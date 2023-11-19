package com.qwict.studyplanetandroid.data.repository

import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity
import com.qwict.studyplanetandroid.data.remote.dto.AuthenticatedUserDto
import com.qwict.studyplanetandroid.data.remote.dto.DiscoverActionDto
import com.qwict.studyplanetandroid.data.remote.dto.ExploreActionDto
import com.qwict.studyplanetandroid.data.remote.dto.HealthDto
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.data.remote.dto.PlanetDto
import com.qwict.studyplanetandroid.data.remote.dto.RegisterDto
import com.qwict.studyplanetandroid.data.remote.dto.UserDto
import com.qwict.studyplanetandroid.domain.model.User
import retrofit2.Response

interface StudyPlanetRepository {
    suspend fun getHealth(): HealthDto
    suspend fun getUserByRemoteId(remoteId: Int): UserRoomEntity
    suspend fun getPlanetsByRemoteId(remoteId: Int): List<PlanetRoomEntity>

    suspend fun insertPlanet(planet: PlanetRoomEntity)
    suspend fun insertPlanets(planets: List<PlanetRoomEntity>)

    suspend fun login(body: LoginDto): AuthenticatedUserDto
    suspend fun authenticate(token: String): AuthenticatedUserDto
    suspend fun register(body: RegisterDto): AuthenticatedUserDto
    suspend fun registerLocalUser(): User
    suspend fun startDiscovering(body: DiscoverActionDto, token: String): Response<Unit>
    suspend fun stopDiscovering(body: DiscoverActionDto, token: String): PlanetDto?
    suspend fun startExploring(body: ExploreActionDto, token: String): Response<Unit>
    suspend fun stopExploring(body: ExploreActionDto, token: String): UserDto
}
