//[app](../../../index.md)/[com.qwict.studyplanetandroid.common](../index.md)/[AuthInterceptor](index.md)/[intercept](intercept.md)

# intercept

[androidJvm]\
open override fun [intercept](intercept.md)(chain: Interceptor.Chain): Response

Intercepts the network request and adds the Authorization header with the user token if available.

#### Return

The Response after processing the intercepted request.

#### Parameters

androidJvm

| | |
|---|---|
| chain | The Interceptor.Chain representing the chain of interceptors. |
