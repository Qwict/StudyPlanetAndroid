package com.qwict.studyplanetandroid.data.remote

import com.qwict.studyplanetandroid.data.remote.dto.AuthenticatedUserDto
import com.qwict.studyplanetandroid.data.remote.dto.DiscoverActionDto
import com.qwict.studyplanetandroid.data.remote.dto.ExploreActionDto
import com.qwict.studyplanetandroid.data.remote.dto.HealthDto
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.data.remote.dto.PlanetDto
import com.qwict.studyplanetandroid.data.remote.dto.RegisterDto
import com.qwict.studyplanetandroid.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface StudyPlanetApi {
    @GET("v1/health/version")
    suspend fun getHealth(): HealthDto

    @POST("v1/users/login")
    suspend fun login(@Body body: LoginDto): AuthenticatedUserDto

    @POST("v1/users/register")
    suspend fun register(@Body body: RegisterDto): AuthenticatedUserDto

    @GET("v1/users")
    suspend fun authenticate(@Header("authorization") bearerToken: String): AuthenticatedUserDto

    @POST("v1/actions/discover")
    suspend fun startDiscovering(@Body body: DiscoverActionDto, @Header("authorization") bearerToken: String): Response<Unit>

    @PUT("v1/actions/discover")
    suspend fun stopDiscovering(@Body body: DiscoverActionDto, @Header("authorization") bearerToken: String): Response<PlanetDto?>

    @POST("v1/actions/explore")
    suspend fun startExploring(@Body body: ExploreActionDto, @Header("authorization") bearerToken: String): Response<Unit>

    @PUT("v1/actions/explore")
    suspend fun stopExploring(@Body body: ExploreActionDto, @Header("authorization") bearerToken: String): UserDto
}
