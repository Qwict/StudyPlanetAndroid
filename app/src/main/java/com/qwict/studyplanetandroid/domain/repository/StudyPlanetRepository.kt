package com.qwict.studyplanetandroid.domain.repository

import com.qwict.studyplanetandroid.data.remote.dto.AuthenticatedUserDto
import com.qwict.studyplanetandroid.data.remote.dto.AuthenticationDto
import com.qwict.studyplanetandroid.data.remote.dto.HealthDto
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.domain.model.User

interface StudyPlanetRepository {
    suspend fun getHealth(): HealthDto
    suspend fun login(body: LoginDto): AuthenticatedUserDto
    suspend fun authenticate(body: AuthenticationDto): AuthenticatedUserDto
    suspend fun registerLocalUser(): User
    suspend fun register(): User
}
