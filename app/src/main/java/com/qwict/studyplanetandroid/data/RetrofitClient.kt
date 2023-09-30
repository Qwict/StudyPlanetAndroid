package com.qwict.studyplanetandroid.data

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://localhost:8090/v1/"

    fun getClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
}