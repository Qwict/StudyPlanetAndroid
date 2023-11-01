package com.qwict.studyplanetandroid.data.repository

import com.qwict.studyplanetandroid.common.AuthenticationSingleton
import com.qwict.studyplanetandroid.common.saveEncryptedPreference
import com.qwict.studyplanetandroid.data.local.OfflinePlanetsRepository
import com.qwict.studyplanetandroid.data.local.OfflineUsersRepository
import com.qwict.studyplanetandroid.data.local.toUser
import com.qwict.studyplanetandroid.data.remote.StudyPlanetApi
import com.qwict.studyplanetandroid.data.remote.dto.AuthenticatedUserDto
import com.qwict.studyplanetandroid.data.remote.dto.AuthenticationDto
import com.qwict.studyplanetandroid.data.remote.dto.HealthDto
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.data.remote.dto.toDatabaseUserWithPlanets
import com.qwict.studyplanetandroid.domain.model.User
import com.qwict.studyplanetandroid.domain.repository.StudyPlanetRepository
import javax.inject.Inject

class StudyPlanetRepositoryImpl @Inject constructor(
    private val api: StudyPlanetApi,
    private val planetRepository: OfflinePlanetsRepository,
    private val userRepository: OfflineUsersRepository,

) : StudyPlanetRepository {
    override suspend fun getHealth(): HealthDto {
        return api.getHealth()
    }

    override suspend fun login(body: LoginDto): AuthenticatedUserDto {
        return api.login(body)
    }

    override suspend fun authenticate(body: AuthenticationDto): AuthenticatedUserDto {
        return api.authenticate(body)
    }

    override suspend fun registerLocalUser(): User {
        throw NotImplementedError()
    }

    override suspend fun register(): User {
        throw NotImplementedError()
    }
}
