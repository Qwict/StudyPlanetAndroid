//[app](../../../index.md)/[com.qwict.studyplanetandroid.domain.use_case.user](../index.md)/[RegisterUseCase](index.md)/[invoke](invoke.md)

# invoke

[androidJvm]\
operator fun [invoke](invoke.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), password: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[Resource](../../com.qwict.studyplanetandroid.common/-resource/index.md)&lt;[User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md)&gt;&gt;

Invokes the registration use case with the provided user details.

#### Return

A Flow emitting [Resource](../../com.qwict.studyplanetandroid.common/-resource/index.md) representing the registration operation result.

#### Parameters

androidJvm

| | |
|---|---|
| name | The user's name. |
| email | The user's email address. |
| password | The user's password. |
