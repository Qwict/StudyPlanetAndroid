package com.qwict.studyplanetandroid.data

import android.util.Log
import com.qwict.studyplanetandroid.common.AuthenticationSingleton
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.data.local.database.OfflinePlanetsRepository
import com.qwict.studyplanetandroid.data.local.database.OfflineUsersRepository
import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity
import com.qwict.studyplanetandroid.data.local.schema.asDomainModel
import com.qwict.studyplanetandroid.data.remote.StudyPlanetApi
import com.qwict.studyplanetandroid.data.remote.dto.AuthenticatedUserDto
import com.qwict.studyplanetandroid.data.remote.dto.DiscoverActionDto
import com.qwict.studyplanetandroid.data.remote.dto.ExploreActionDto
import com.qwict.studyplanetandroid.data.remote.dto.HealthDto
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.data.remote.dto.PlanetDto
import com.qwict.studyplanetandroid.data.remote.dto.RegisterDto
import com.qwict.studyplanetandroid.data.remote.dto.asDatabaseModel
import com.qwict.studyplanetandroid.data.remote.dto.toDatabaseUserWithPlanets
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

interface StudyPlanetRepository {
    fun getActiveUser(): Flow<User>
    fun getDiscoveredPlanets(): Flow<List<Planet>>
    suspend fun refreshDiscoveredPlanetsOnline(): Flow<Resource<Unit>>

    suspend fun getHealth(): HealthDto
    suspend fun getUserByRemoteId(remoteId: Int): UserRoomEntity
    suspend fun insertPlanet(planet: PlanetRoomEntity)
    suspend fun insertPlanets(planets: List<PlanetRoomEntity>)

    suspend fun login(body: LoginDto): AuthenticatedUserDto
    suspend fun authenticate(token: String): AuthenticatedUserDto
    suspend fun register(body: RegisterDto): AuthenticatedUserDto
    suspend fun registerLocalUser(): User
    suspend fun startDiscovering(body: DiscoverActionDto): Response<Unit>
    suspend fun stopDiscovering(body: DiscoverActionDto): PlanetDto?
    suspend fun startExploring(body: ExploreActionDto): Response<Unit>
    suspend fun stopExploring(body: ExploreActionDto): Int
}

/**
 * Implementation of the [StudyPlanetRepository] interface that communicates with both local and remote data sources.
 * Application of facade pattern to abstract away the implementation details of the data sources.
 *
 * @param api The [StudyPlanetApi] instance for making remote API calls.
 * @param userDatabase The [OfflineUsersRepository] for local user-related database operations.
 * @param planetDatabase The [OfflinePlanetsRepository] for local planet-related database operations.
 */
class StudyPlanetRepositoryImpl @Inject constructor(
    private val api: StudyPlanetApi,
    private val userDatabase: OfflineUsersRepository,
    private val planetDatabase: OfflinePlanetsRepository,
) : StudyPlanetRepository {
    override fun getActiveUser(): Flow<User> {
        if (isUserAuthenticated) {
            val remoteId = AuthenticationSingleton.remoteUserId
            Log.d("StudyPlanetRepository", "getActiveUser: $remoteId")
            return userDatabase.getUserFlowByRemoteId(remoteId).map { it.asDomainModel() }
        } else {
            throw Exception("User is not authenticated")
        }
    }

    override fun getDiscoveredPlanets(): Flow<List<Planet>> {
        return planetDatabase.getPlanetsByOwnerId(AuthenticationSingleton.remoteUserId).map {
            it.map { planet -> planet.asDomainModel() }
        }
    }

    override suspend fun refreshDiscoveredPlanetsOnline(): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            Log.i("StudyPlanetRepository", "refreshDiscoveredPlanetsOnline")
            val authenticatedUserDto = api.authenticate()
            val userWithPlanets = authenticatedUserDto.toDatabaseUserWithPlanets()
            planetDatabase.removeAllByOwnerId(AuthenticationSingleton.remoteUserId)
            planetDatabase.insertAll(userWithPlanets.planets)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    /**
     * Retrieves the health information of the Study Planet server.
     *
     * @return The [HealthDto] containing health and version information.
     */
    override suspend fun getHealth(): HealthDto {
        return api.getHealth()
    }

    /**
     * Retrieves a user from the local database based on their remote ID.
     *
     * @param remoteId The remote ID of the user.
     * @return The [UserRoomEntity] representing the user.
     */
    override suspend fun getUserByRemoteId(remoteId: Int): UserRoomEntity {
        return userDatabase.getUserByRemoteId(remoteId)
    }

    /**
     * Inserts a single planet into the local database.
     *
     * @param planet The [PlanetRoomEntity] to be inserted.
     */
    override suspend fun insertPlanet(planet: PlanetRoomEntity) {
        planetDatabase.insert(planet)
    }

    /**
     * Inserts a list of planets into the local database.
     *
     * @param planets The list of [PlanetRoomEntity] to be inserted.
     */
    override suspend fun insertPlanets(planets: List<PlanetRoomEntity>) {
        planetDatabase.insertAll(planets)
    }

    /**
     * Registers a new user remotely and performs local database operations to store the user and associated planets.
     *
     * @param body The [RegisterDto] containing user registration information.
     * @return The [AuthenticatedUserDto] representing the registered and authenticated user.
     */
    override suspend fun register(body: RegisterDto): AuthenticatedUserDto {
        return api.register(body)
    }

    /**
     * Logs in a user remotely and performs local database operations to store the user and associated planets.
     *
     * @param body The [LoginDto] containing login credentials.
     * @return The [AuthenticatedUserDto] representing the authenticated user.
     */
    override suspend fun login(body: LoginDto): AuthenticatedUserDto {
        val authenticatedUserDto = api.login(body)
        val userWithPlanets = authenticatedUserDto.toDatabaseUserWithPlanets()
        userDatabase.insert(userWithPlanets.user)
        planetDatabase.insertAll(userWithPlanets.planets)
        return authenticatedUserDto
    }

    /**
     * Authenticates the current user remotely and performs local database operations to store the user and associated planets.
     *
     * @param token The authentication token.
     * @return The [AuthenticatedUserDto] representing the authenticated user.
     */
    override suspend fun authenticate(token: String): AuthenticatedUserDto {
        val authenticatedUserDto = api.authenticate()
        val userWithPlanets = authenticatedUserDto.toDatabaseUserWithPlanets()
        userDatabase.insert(userWithPlanets.user)
        planetDatabase.insertAll(userWithPlanets.planets)
        return authenticatedUserDto
    }

    /**
     * Placeholder method for registering a local user. Not implemented.
     *
     * @throws NotImplementedError This method is not yet implemented.
     */
    override suspend fun registerLocalUser(): User {
        throw NotImplementedError()
    }

    /**
     * Initiates the process of discovering a new planet remotely.
     *
     * @param body The [DiscoverActionDto] containing information for the discovery action.
     * @return The [Response] indicating the success or failure of the discovery action.
     */
    override suspend fun startDiscovering(body: DiscoverActionDto): Response<Unit> {
        return api.startDiscovering(body)
    }

    /**
     * Stops the process of discovering a planet remotely and performs local database operations if successful.
     *
     * @param body The [DiscoverActionDto] containing information for stopping the discovery action.
     * @return The [PlanetDto] representing the discovered planet, or null if the discovery was unsuccessful.
     */
    override suspend fun stopDiscovering(body: DiscoverActionDto): PlanetDto? {
        val response = api.stopDiscovering(body)
        if (response.hasFoundNewPlanet) {
            planetDatabase.insert(response.planet.asDatabaseModel(AuthenticationSingleton.remoteUserId))
            return response.planet
        }
        return null
    }

    /**
     * Initiates the process of exploring a planet remotely.
     *
     * @param body The [ExploreActionDto] containing information for the exploration action.
     * @return The [Response] indicating the success or failure of the exploration action.
     */
    override suspend fun startExploring(body: ExploreActionDto): Response<Unit> {
        return api.startExploring(body)
    }

    override suspend fun stopExploring(body: ExploreActionDto): Int {
        val response = api.stopExploring(body)
        val user = userDatabase.getUserByRemoteId(AuthenticationSingleton.remoteUserId)
        user.experience += response.experience
        userDatabase.update(user)
        return response.experience
    }
}
