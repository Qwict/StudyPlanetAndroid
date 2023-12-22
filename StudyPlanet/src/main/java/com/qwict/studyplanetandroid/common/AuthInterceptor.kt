package com.qwict.studyplanetandroid.common

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor for adding the Authorization header with the user token to outgoing network requests.
 */
class AuthInterceptor : Interceptor {
    /**
     * Intercepts the network request and adds the Authorization header with the user token if available.
     *
     * @param chain The [Interceptor.Chain] representing the chain of interceptors.
     * @return The [Response] after processing the intercepted request.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        getEncryptedPreference("token")?.let {
            requestBuilder.addHeader("Authorization", it)
        }
        return chain.proceed(requestBuilder.build())
    }
}
