//[app](../../../index.md)/[com.qwict.studyplanetandroid.domain.use_case.actions](../index.md)/[StopDiscoveringUseCase](index.md)

# StopDiscoveringUseCase

[androidJvm]\
class [StopDiscoveringUseCase](index.md)@Injectconstructor(repo: [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md))

Use case for stopping the process of discovering a new planet.

## Constructors

| | |
|---|---|
| [StopDiscoveringUseCase](-stop-discovering-use-case.md) | [androidJvm]<br>@Inject<br>constructor(repo: [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | [androidJvm]<br>operator fun [invoke](invoke.md)(selectedTime: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): Flow&lt;[Resource](../../com.qwict.studyplanetandroid.common/-resource/index.md)&lt;[Planet](../../com.qwict.studyplanetandroid.domain.model/-planet/index.md)?&gt;&gt;<br>Invokes the use case to stop the process of discovering a new planet. |
