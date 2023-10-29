package com.qwict.studyplanetandroid.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.qwict.studyplanetandroid.common.Constants.BASE_URL
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
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    // TODO: What does this even mean? (@JvmSuppressWildcards)
    // TODO: I should use an interceptor for this but there is no good tutorial that I can find...
    @POST("/v1/actions/discover")
    @JvmSuppressWildcards
    fun startDiscovery(@Header("authorization") bearerToken: String, @Body body: JsonObject): Call<JsonObject>

    @PUT("/v1/actions/discover")
    @JvmSuppressWildcards
    fun finishedDiscovery(@Header("authorization") bearerToken: String): Call<JsonObject>

    @POST("/v1/actions/explore")
    @JvmSuppressWildcards
    fun startExploring(@Header("authorization") bearerToken: String, @Body body: JsonObject): Call<JsonObject>

    @PUT("/v1/actions/explore")
    @JvmSuppressWildcards
    fun finishedExploration(@Header("authorization") bearerToken: String): Call<JsonObject>

    @POST("/v1/users/register")
    @JvmSuppressWildcards
    fun register(@Body body: JsonObject): Call<JsonObject>

    @POST("/v1/users/login")
    @JvmSuppressWildcards
    fun login(@Body body: JsonObject): Call<JsonObject>

    @GET("/v1/users/{id}")
    fun getUserById(@Header("authorization") bearerToken: String, @Path("id") id: Int): Call<JsonObject>

    //    TODO: should all these be suspend functions? Or not because it returns a Call? Otherwise return a JsonObject
    @Headers(
        "Accept: application/json",
    )
    @GET("/v1/health/version/")
    fun getVersion(): Call<JsonObject>
}

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
