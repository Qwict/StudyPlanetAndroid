package com.qwict.studyplanetandroid.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.qwict.studyplanetandroid.dto.HealthDto
import com.qwict.studyplanetandroid.dto.UserDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("users/explore")
    fun explore(): Call<UserDto>

    @Headers(
        "Accept: application/json",
    )
    @GET("users/{id}")
    abstract fun getUserByAuth0Id(
        @Path("id") id: String,
    ): Call<UserDto?>?

    // TODO: What does this even mean? (@JvmSuppressWildcards)
    // TODO: I should use an interceptor for this but there is no good tutorial that I can find...
    @POST("users/actions")
    @JvmSuppressWildcards
    fun handleAction(@Header("authorization") bearerToken: String, @Body body: JsonObject): Call<JsonObject>

    @POST("users/register")
    @JvmSuppressWildcards
    fun register(@Body body: Map<String, Any>): Call<JsonObject>

    @POST("users/login")
    @JvmSuppressWildcards
    fun login(@Body body: JsonObject): Call<JsonObject>

    @GET("users/{id}")
    fun getUserById(@Header("authorization") bearerToken: String, @Path("id") id: Int): Call<JsonObject>

    //    TODO: should all these be suspend functions? Or not because it returns a Call? Otherwise return a JsonObject
    @Headers(
        "Accept: application/json",
    )
    @GET("health/version/")
    fun getVersion(): Call<JsonObject>
}

private const val BASE_URL =
    "http://10.0.2.2:9010/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        Json.asConverterFactory("application/json".toMediaType()),
    )
    .baseUrl(BASE_URL).build()

object Api {
    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
