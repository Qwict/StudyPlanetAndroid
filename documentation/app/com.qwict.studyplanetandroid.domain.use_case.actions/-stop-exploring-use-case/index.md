//[app](../../../index.md)/[com.qwict.studyplanetandroid.domain.use_case.actions](../index.md)/[StopExploringUseCase](index.md)

# StopExploringUseCase

[androidJvm]\
class [StopExploringUseCase](index.md)@Injectconstructor(repo: [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md))

Use case for stopping the process of exploring a discovered planet.

## Constructors

| | |
|---|---|
| [StopExploringUseCase](-stop-exploring-use-case.md) | [androidJvm]<br>@Inject<br>constructor(repo: [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | [androidJvm]<br>operator fun [invoke](invoke.md)(selectedTime: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), selectedPlanetId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): Flow&lt;[Resource](../../com.qwict.studyplanetandroid.common/-resource/index.md)&lt;[User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md)&gt;&gt;<br>Invokes the use case to stop the process of exploring a discovered planet. |
