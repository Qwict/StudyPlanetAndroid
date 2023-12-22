//[app](../../../index.md)/[com.qwict.studyplanetandroid.domain.use_case.user](../index.md)/[RegisterUseCase](index.md)

# RegisterUseCase

class [RegisterUseCase](index.md)@Injectconstructor(repo: [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md))

Use case for handling user registration.

#### Parameters

androidJvm

| | |
|---|---|
| repo | The repository providing access to the study planet data. |

## Constructors

| | |
|---|---|
| [RegisterUseCase](-register-use-case.md) | [androidJvm]<br>@Inject<br>constructor(repo: [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | [androidJvm]<br>operator fun [invoke](invoke.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), password: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[Resource](../../com.qwict.studyplanetandroid.common/-resource/index.md)&lt;[User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md)&gt;&gt;<br>Invokes the registration use case with the provided user details. |
