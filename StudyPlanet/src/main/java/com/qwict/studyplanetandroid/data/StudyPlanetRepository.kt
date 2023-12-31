package com.qwict.studyplanetandroid.data

import android.util.Log
import com.qwict.studyplanetandroid.StudyPlanetApplication
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.data.local.dao.PlanetDao
import com.qwict.studyplanetandroid.data.local.dao.UserDao
import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity
import com.qwict.studyplanetandroid.data.local.schema.asDomainModel
import com.qwict.studyplanetandroid.data.remote.StudyPlanetApi
import com.qwict.studyplanetandroid.data.remote.dto.AuthenticatedUserDto
import com.qwict.studyplanetandroid.data.remote.dto.DiscoverActionDto
import com.qwict.studyplanetandroid.data.remote.dto.ExploreActionDto
import com.qwict.studyplanetandroid.data.remote.dto.HealthDto
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.data.remote.dto.RegisterDto
import com.qwict.studyplanetandroid.data.remote.dto.asDatabaseEntityWithPlanets
import com.qwict.studyplanetandroid.data.remote.dto.asDatabaseModel
import com.qwict.studyplanetandroid.data.remote.dto.asDomainModel
import com.qwict.studyplanetandroid.domain.model.ActionResponse
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

    suspend fun insertUser(user: UserRoomEntity)

    suspend fun insertPlanet(planet: PlanetRoomEntity)

    suspend fun insertPlanets(planets: List<PlanetRoomEntity>)

    suspend fun login(body: LoginDto): AuthenticatedUserDto

    suspend fun authenticate(token: String): AuthenticatedUserDto

    suspend fun register(body: RegisterDto): AuthenticatedUserDto

    suspend fun startDiscovering(body: DiscoverActionDto): Response<Unit>

    suspend fun stopDiscovering(body: DiscoverActionDto): ActionResponse

    suspend fun startExploring(body: ExploreActionDto): Response<Unit>

    suspend fun stopExploring(body: ExploreActionDto): Int
}

/**
 * Implementation of the [StudyPlanetRepository] interface that communicates with both local and remote data sources.
 * Application of facade pattern to abstract away the implementation details of the data sources.
 *
 * @param api The [StudyPlanetApi] instance for making remote API calls.
 * @param userDao The [UserDao] for local user-related database operations.
 * @param planetDao The [PlanetDao] for local planet-related database operations.
 */
class StudyPlanetRepositoryImpl
    @Inject
    constructor(
        private val api: StudyPlanetApi,
        private val userDao: UserDao,
        private val planetDao: PlanetDao,
    ) : StudyPlanetRepository {
        override fun getActiveUser(): Flow<User> {
            if (StudyPlanetApplication.authSingleton.isUserAuthenticated) {
                val remoteId = StudyPlanetApplication.authSingleton.remoteUserId
                Log.d("StudyPlanetRepository", "getActiveUser: $remoteId")
                return userDao.getUserFlowByRemoteId(remoteId).map { it.asDomainModel() }
            } else {
                throw Exception("User is not authenticated")
            }
        }

        override fun getDiscoveredPlanets(): Flow<List<Planet>> {
            return planetDao.getPlanetsFlowByOwnerId(StudyPlanetApplication.authSingleton.remoteUserId).map {
                it.map { planet -> planet.asDomainModel() }
            }
        }

        override suspend fun refreshDiscoveredPlanetsOnline(): Flow<Resource<Unit>> =
            flow {
                emit(Resource.Loading())
                try {
                    Log.i("StudyPlanetRepository", "refreshDiscoveredPlanetsOnline")
                    val authenticatedUserDto = api.authenticate()
                    val remotePlanets = authenticatedUserDto.asDatabaseEntityWithPlanets().planets
                    val localPlanets = planetDao.getPlanetsByOwnerId(StudyPlanetApplication.authSingleton.remoteUserId)

                    localPlanets.forEach { localPlanet ->
                        if (!remotePlanets.contains(localPlanet)) {
                            planetDao.delete(localPlanet)
                        }
                    }

                    planetDao.insertAll(remotePlanets)
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
            return userDao.getUserByRemoteId(remoteId)
        }

        override suspend fun insertUser(user: UserRoomEntity) {
            userDao.insert(user)
        }

        /**
         * Inserts a single planet into the local database.
         *
         * @param planet The [PlanetRoomEntity] to be inserted.
         */
        override suspend fun insertPlanet(planet: PlanetRoomEntity) {
            planetDao.insert(planet)
        }

        /**
         * Inserts a list of planets into the local database.
         *
         * @param planets The list of [PlanetRoomEntity] to be inserted.
         */
        override suspend fun insertPlanets(planets: List<PlanetRoomEntity>) {
            planetDao.insertAll(planets)
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
            val userWithPlanets = authenticatedUserDto.asDatabaseEntityWithPlanets()
            userDao.insert(userWithPlanets.user)
            planetDao.insertAll(userWithPlanets.planets)
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
            val userWithPlanets = authenticatedUserDto.asDatabaseEntityWithPlanets()
            userDao.insert(userWithPlanets.user)
            planetDao.insertAll(userWithPlanets.planets)
            return authenticatedUserDto
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
         * @return The [ActionResponse] containing information about the discovery action.
         */
        override suspend fun stopDiscovering(body: DiscoverActionDto): ActionResponse {
            val response = api.stopDiscovering(body)
            if (response.hasFoundNewPlanet && response.planet != null) {
                val planetRoomEntity = response.planet.asDatabaseModel(StudyPlanetApplication.authSingleton.remoteUserId)
                planetDao.insert(planetRoomEntity)
            }
            val activeUser = userDao.getUserByRemoteId(StudyPlanetApplication.authSingleton.remoteUserId)
            val updatedUser = activeUser.copy(experience = activeUser.experience + response.experience)
            userDao.update(updatedUser)
            return response.asDomainModel(response.planet?.asDatabaseModel(StudyPlanetApplication.authSingleton.remoteUserId))
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
            val activeUser = userDao.getUserByRemoteId(StudyPlanetApplication.authSingleton.remoteUserId)
            val updatedUser = activeUser.copy(experience = activeUser.experience + response.experience)
            userDao.update(updatedUser)
            return response.experience
        }
    }
