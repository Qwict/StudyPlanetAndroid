package com.qwict.studyplanetandroid.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitClient {

//    private const val BASE_URL = "http://localhost:9009/v1/"
    private const val BASE_URL = "https://todo.qwict.com/api/"

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(RequestInterceptor)
        .build()
    fun getClient(): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

}

//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object RetrofitClient {
//    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
//
//    val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//}
//
//object ApiClient {
//    val apiService: ApiService by lazy {
//        RetrofitClient.retrofit.create(ApiService::class.java)
//    }
//}