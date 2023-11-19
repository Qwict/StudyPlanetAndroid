package com.qwict.studyplanetandroid.data.repository

import com.qwict.studyplanetandroid.data.local.database.OfflinePlanetsRepository
import com.qwict.studyplanetandroid.data.local.database.OfflineUsersRepository
import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity
import com.qwict.studyplanetandroid.data.remote.StudyPlanetApi
import com.qwict.studyplanetandroid.data.remote.dto.AuthenticatedUserDto
import com.qwict.studyplanetandroid.data.remote.dto.DiscoverActionDto
import com.qwict.studyplanetandroid.data.remote.dto.ExploreActionDto
import com.qwict.studyplanetandroid.data.remote.dto.HealthDto
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.data.remote.dto.PlanetDto
import com.qwict.studyplanetandroid.data.remote.dto.RegisterDto
import com.qwict.studyplanetandroid.data.remote.dto.UserDto
import com.qwict.studyplanetandroid.data.remote.dto.toDatabaseUserWithPlanets
import com.qwict.studyplanetandroid.domain.model.User
import retrofit2.Response
import javax.inject.Inject

class StudyPlanetRepositoryImpl @Inject constructor(
    private val api: StudyPlanetApi,
    private val userDatabase: OfflineUsersRepository,
    private val planetDatabase: OfflinePlanetsRepository,
) : StudyPlanetRepository {
    override suspend fun getHealth(): HealthDto {
        return api.getHealth()
    }

    override suspend fun getUserByRemoteId(remoteId: Int): UserRoomEntity {
        return userDatabase.getUserByRemoteId(remoteId)
    }

    override suspend fun getPlanetsByRemoteId(remoteId: Int): List<PlanetRoomEntity> {
        return planetDatabase.getPlanetsByRemoteId(remoteId)
    }

    override suspend fun insertPlanet(planet: PlanetRoomEntity) {
        planetDatabase.insert(planet)
    }

    override suspend fun insertPlanets(planets: List<PlanetRoomEntity>) {
        planetDatabase.insertAll(planets)
    }

    override suspend fun register(body: RegisterDto): AuthenticatedUserDto {
        return api.register(body)
    }

    override suspend fun login(body: LoginDto): AuthenticatedUserDto {
        val authenticatedUserDto = api.login(body)
        val userWithPlanets = authenticatedUserDto.toDatabaseUserWithPlanets()
        userDatabase.insert(userWithPlanets.user)
        planetDatabase.insertAll(userWithPlanets.planets)
        return authenticatedUserDto
    }

    override suspend fun authenticate(token: String): AuthenticatedUserDto {
        val authenticatedUserDto = api.authenticate(token)
        val userWithPlanets = authenticatedUserDto.toDatabaseUserWithPlanets()
        userDatabase.insert(userWithPlanets.user)
        planetDatabase.insertAll(userWithPlanets.planets)
        return authenticatedUserDto
    }

    override suspend fun registerLocalUser(): User {
        throw NotImplementedError()
    }

    override suspend fun startDiscovering(body: DiscoverActionDto, token: String): Response<Unit> {
        return api.startDiscovering(body, token)
    }

    override suspend fun stopDiscovering(body: DiscoverActionDto, token: String): PlanetDto? {
        return api.stopDiscovering(body, token).body()
    }

    override suspend fun startExploring(body: ExploreActionDto, token: String): Response<Unit> {
        return api.startExploring(body, token)
    }

    override suspend fun stopExploring(body: ExploreActionDto, token: String): UserDto {
        return api.stopExploring(body, token)
    }
}
