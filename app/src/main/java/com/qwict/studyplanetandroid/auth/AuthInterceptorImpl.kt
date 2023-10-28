package com.qwict.studyplanetandroid.auth
// TODO: Hoe moet een interceptor werken? Also is er ergens een goede tutorial, alles is brak
//
//import okhttp3.Interceptor
//import okhttp3.Request
//import okhttp3.Response
//import java.net.HttpURLConnection
//
//class AuthInterceptorImpl : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//        val accessToken = "fdjlskfjlksdjflsd"
//
//        val response = chain.proceed(newRequestWithAccessToken(accessToken, request))
//
//        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
//            val newAccessToken = .getAccessToken()
//            if (newAccessToken != accessToken) {
//                return chain.proceed(newRequestWithAccessToken(accessToken, request))
//            } else {
//                accessToken = refreshToken()
//                if (accessToken.isNullOrBlank()) {
//                    sessionManager.logout()
//                    return response
//                }
//                return chain.proceed(newRequestWithAccessToken(accessToken, request))
//            }
//        }
//
//        return response
//    }
//
//    private fun newRequestWithAccessToken(accessToken: String?, request: Request): Request =
//        request.newBuilder()
//            .header("Authorization", "Bearer $accessToken")
//            .build()
//
//    private fun refreshToken(): String? {
//        synchronized(this) {
//            val refreshToken = sessionManager.getRefreshToken()
//            refreshToken?.let {
//                return sessionManager.refreshToken(refreshToken)
//            } ?: return null
//        }
//    }
//}