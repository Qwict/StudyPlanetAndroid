//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.remote](../index.md)/[StudyPlanetApi](index.md)/[startExploring](start-exploring.md)

# startExploring

[androidJvm]\

@POST(value = &quot;v1/actions/explore&quot;)

abstract suspend fun [startExploring](start-exploring.md)(@Bodybody: [ExploreActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-explore-action-dto/index.md)): Response&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)&gt;

Initiates the process of exploring a planet.

#### Parameters

androidJvm

| | |
|---|---|
| body | The [ExploreActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-explore-action-dto/index.md) containing information for the exploration action. |
