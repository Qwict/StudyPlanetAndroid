//[app](../../../index.md)/[com.qwict.studyplanetandroid.domain.use_case.planets](../index.md)/[GetOnlinePlanetsUseCase](index.md)

# GetOnlinePlanetsUseCase

[androidJvm]\
class [GetOnlinePlanetsUseCase](index.md)@Injectconstructor(repo: [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md))

Use case for fetching online planets based on user authentication status.

## Constructors

| | |
|---|---|
| [GetOnlinePlanetsUseCase](-get-online-planets-use-case.md) | [androidJvm]<br>@Inject<br>constructor(repo: [StudyPlanetRepository](../../com.qwict.studyplanetandroid.data.repository/-study-planet-repository/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | [androidJvm]<br>operator fun [invoke](invoke.md)(): Flow&lt;[Resource](../../com.qwict.studyplanetandroid.common/-resource/index.md)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Planet](../../com.qwict.studyplanetandroid.domain.model/-planet/index.md)&gt;&gt;&gt;<br>Invokes the use case to fetch online planets. |
