//[app](../../../index.md)/[com.qwict.studyplanetandroid.domain.use_case.user](../index.md)/[LoginUseCase](index.md)/[invoke](invoke.md)

# invoke

[androidJvm]\
operator fun [invoke](invoke.md)(email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), password: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[Resource](../../com.qwict.studyplanetandroid.common/-resource/index.md)&lt;[User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md)&gt;&gt;

Invokes the login use case with the provided email and password.

#### Return

A Flow emitting [Resource](../../com.qwict.studyplanetandroid.common/-resource/index.md) representing the login operation result.

#### Parameters

androidJvm

| | |
|---|---|
| email | The user's email address. |
| password | The user's password. |
