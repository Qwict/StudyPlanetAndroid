package com.qwict.studyplanetandroid.data.remote

import com.qwict.studyplanetandroid.data.remote.dto.AuthenticatedUserDto
import com.qwict.studyplanetandroid.data.remote.dto.AuthenticationDto
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.data.remote.dto.HealthDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StudyPlanetApi {
    @GET("/v1/health/version")
    suspend fun getHealth(): HealthDto

    @POST("v1/users/login")
    suspend fun login(@Body body: LoginDto): AuthenticatedUserDto

    @GET("v1/users")
    suspend fun authenticate(@Body body: AuthenticationDto): AuthenticatedUserDto
}
