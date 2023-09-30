package com.qwict.studyplanetandroid.api

import com.qwict.studyplanetandroid.dto.HealthDto
import retrofit2.Call
import retrofit2.http.GET

interface HealthApi {
    @GET("health/version")
    fun getHealth(): Call<HealthDto>
}