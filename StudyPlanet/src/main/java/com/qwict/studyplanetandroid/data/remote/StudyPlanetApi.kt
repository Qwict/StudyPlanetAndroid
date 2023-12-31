package com.qwict.studyplanetandroid.data.remote

import com.qwict.studyplanetandroid.data.remote.dto.AuthenticatedUserDto
import com.qwict.studyplanetandroid.data.remote.dto.DiscoverActionDto
import com.qwict.studyplanetandroid.data.remote.dto.DiscoverActionResponseDto
import com.qwict.studyplanetandroid.data.remote.dto.ExploreActionDto
import com.qwict.studyplanetandroid.data.remote.dto.ExploreActionResponseDto
import com.qwict.studyplanetandroid.data.remote.dto.HealthDto
import com.qwict.studyplanetandroid.data.remote.dto.LoginDto
import com.qwict.studyplanetandroid.data.remote.dto.PlanetDto
import com.qwict.studyplanetandroid.data.remote.dto.RegisterDto
import com.qwict.studyplanetandroid.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

/**
 * Retrofit API interface for Study Planet, defining various HTTP methods for interacting with the Study Planet server.
 */
interface StudyPlanetApi {
    /**
     * Checks the health and version of the Study Planet server.
     *
     * @return The [HealthDto] representing the health and version information.
     */
    @GET("v1/health/version")
    suspend fun getHealth(): HealthDto

    /**
     * Attempts to log in a user with the provided [LoginDto].
     *
     * @param body The [LoginDto] containing login credentials.
     * @return The [AuthenticatedUserDto] representing the authenticated user.
     */
    @POST("v1/users/login")
    suspend fun login(@Body body: LoginDto): AuthenticatedUserDto

    /**
     * Registers a new user with the provided [RegisterDto].
     *
     * @param body The [RegisterDto] containing user registration information.
     * @return The [AuthenticatedUserDto] representing the registered and authenticated user.
     */
    @POST("v1/users/register")
    suspend fun register(@Body body: RegisterDto): AuthenticatedUserDto

    /**
     * Authenticates the current user.
     *
     * @return The [AuthenticatedUserDto] representing the authenticated user.
     */
    @GET("v1/users")
    suspend fun authenticate(): AuthenticatedUserDto

    /**
     * Initiates the process of discovering a new planet.
     *
     * @param body The [DiscoverActionDto] containing information for the discovery action.
     */
    @POST("v1/actions/discover")
    suspend fun startDiscovering(@Body body: DiscoverActionDto): Response<Unit>

    /**
     * Stops the process of discovering a planet.
     *
     * @param body The [DiscoverActionDto] containing information for stopping the discovery action.
     * @return The [PlanetDto] representing the discovered planet, or null if the discovery was unsuccessful.
     */
    @PUT("v1/actions/discover")
    suspend fun stopDiscovering(@Body body: DiscoverActionDto): DiscoverActionResponseDto

    /**
     * Initiates the process of exploring a planet.
     *
     * @param body The [ExploreActionDto] containing information for the exploration action.
     */
    @POST("v1/actions/explore")
    suspend fun startExploring(@Body body: ExploreActionDto): Response<Unit>

    /**
     * Stops the process of exploring a planet.
     *
     * @param body The [ExploreActionDto] containing information for stopping the exploration action.
     * @return The [UserDto] representing the user after exploration.
     */
    @PUT("v1/actions/explore")
    suspend fun stopExploring(@Body body: ExploreActionDto): ExploreActionResponseDto
}
