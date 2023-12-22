//[app](../../../index.md)/[com.qwict.studyplanetandroid.common.di](../index.md)/[AppModule](index.md)/[okhttpClient](okhttp-client.md)

# okhttpClient

[androidJvm]\

@Provides

@Singleton

fun [okhttpClient](okhttp-client.md)(): OkHttpClient

Provides an instance of OkHttpClient configured with an authentication interceptor.

This function creates and configures an OkHttpClient instance for handling HTTP requests. It adds an [AuthInterceptor](../../com.qwict.studyplanetandroid.common/-auth-interceptor/index.md) to the OkHttpClient to include authentication headers in requests. The OkHttpClient is configured as a singleton, meaning the same instance is reused throughout the application.

#### Return

An instance of OkHttpClient with authentication capabilities.
