package com.qwict.studyplanetandroid.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

object RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
//        Log.i("Outgoing Request", request.toString())
        return chain.proceed(request)
    }
}