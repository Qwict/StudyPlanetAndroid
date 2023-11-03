package com.qwict.studyplanetandroid.data.repository

import com.qwict.studyplanetandroid.data.local.OfflinePlanetsRepository
import com.qwict.studyplanetandroid.data.local.OfflineUsersRepository
import com.qwict.studyplanetandroid.data.remote.StudyPlanetApi
import com.qwict.studyplanetandroid.data.remote.dto.AuthenticatedUserDto
import com.qwict.studyplanetandroid.data.remote.dto.DiscoverActionDto
import com.qwict.studyplanetandroid.data.remote.dto.ExploreActionDto
import com.qwict.studyplanetandroid.data.remote.dto.HealthDto
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.data.remote.dto.PlanetDto
import com.qwict.studyplanetandroid.data.remote.dto.RegisterDto
import com.qwict.studyplanetandroid.data.remote.dto.UserDto
import com.qwict.studyplanetandroid.domain.model.User
import com.qwict.studyplanetandroid.domain.repository.StudyPlanetRepository
import retrofit2.Response
import javax.inject.Inject

class StudyPlanetRepositoryImpl @Inject constructor(
    private val api: StudyPlanetApi,
    private val planetRepository: OfflinePlanetsRepository,
    private val userRepository: OfflineUsersRepository,
) : StudyPlanetRepository {
    override suspend fun getHealth(): HealthDto {
        return api.getHealth()
    }

    override suspend fun register(body: RegisterDto): AuthenticatedUserDto {
        return api.register(body)
    }

    override suspend fun login(body: LoginDto): AuthenticatedUserDto {
        return api.login(body)
    }

    override suspend fun authenticate(token: String): AuthenticatedUserDto {
        return api.authenticate(token)
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
