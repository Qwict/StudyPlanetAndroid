package com.qwict.studyplanetandroid

import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.data.StudyPlanetRepository
import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity
import com.qwict.studyplanetandroid.data.remote.dto.AuthenticatedUserDto
import com.qwict.studyplanetandroid.data.remote.dto.DiscoverActionDto
import com.qwict.studyplanetandroid.data.remote.dto.ExploreActionDto
import com.qwict.studyplanetandroid.data.remote.dto.HealthDto
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.data.remote.dto.RegisterDto
import com.qwict.studyplanetandroid.data.remote.dto.UserDto
import com.qwict.studyplanetandroid.domain.model.ActionResponse
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.domain.model.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class FakeStudyPlanetRepository : StudyPlanetRepository {
    private var databaseUser =
        UserRoomEntity(
            id = 1,
            experience = 121,
            name = "Test",
            remoteId = 1,
            email = "test@test.com",
        )

    private var userDto =
        UserDto(
            id = 1,
            name = "Test",
            email = "test@test.com",
            experience = 121,
            discoveredPlanets = emptyList(),
        )

    private var authenticatedUserDto =
        AuthenticatedUserDto(
            user = userDto,
            token = "token",
            validated = true,
        )

    private var planets = emptyList<PlanetRoomEntity>()

    override fun getActiveUser(): Flow<User> {
        TODO("Not yet implemented")
    }

    override fun getDiscoveredPlanets(): Flow<List<Planet>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshDiscoveredPlanetsOnline(): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHealth(): HealthDto {
        return HealthDto(
            env = "TEST",
            name = "study_planet_api",
            version = "1.0.0",
            androidVersion = "1.0.0",
            iosVersion = "1.0.0",
        )
    }

    override suspend fun getUserByRemoteId(remoteId: Int): UserRoomEntity {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: UserRoomEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertPlanet(planet: PlanetRoomEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertPlanets(planets: List<PlanetRoomEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun login(loginDto: LoginDto): AuthenticatedUserDto {
        if (loginDto.email.isBlank()) {
            throw Exception("Email is blank")
        }
        return authenticatedUserDto
    }

    override suspend fun authenticate(token: String): AuthenticatedUserDto {
        return authenticatedUserDto
    }

    override suspend fun register(body: RegisterDto): AuthenticatedUserDto {
        TODO("Not yet implemented")
    }

    override suspend fun startDiscovering(body: DiscoverActionDto): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun stopDiscovering(body: DiscoverActionDto): ActionResponse {
        TODO("Not yet implemented")
    }

    override suspend fun startExploring(body: ExploreActionDto): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun stopExploring(body: ExploreActionDto): Int {
        TODO("Not yet implemented")
    }
}
