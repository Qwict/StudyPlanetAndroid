package com.qwict.studyplanetandroid.helper

import com.qwict.studyplanetandroid.api.IApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton {
    private val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("http://192.168.1.47:9010/v1/")
        .baseUrl("http://10.0.2.2:9010/v1/")
        .addConverterFactory(GsonConverterFactory.create())
//        TODO: I need to add an interceptor...
//        .client(OkHttpClient.Builder()
//            .addInterceptor(authInterceptor)
//            .build())
        .build()

    private val apiService = retrofit.create(IApiService::class.java)

    fun getApiService(): IApiService {
        return apiService
    }
}
