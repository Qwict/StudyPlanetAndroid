//[app](../../../index.md)/[com.qwict.studyplanetandroid.common](../index.md)/[AuthInterceptor](index.md)

# AuthInterceptor

[androidJvm]\
class [AuthInterceptor](index.md) : Interceptor

Interceptor for adding the Authorization header with the user token to outgoing network requests.

## Constructors

| | |
|---|---|
| [AuthInterceptor](-auth-interceptor.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | [androidJvm]<br>open override fun [intercept](intercept.md)(chain: Interceptor.Chain): Response<br>Intercepts the network request and adds the Authorization header with the user token if available. |
