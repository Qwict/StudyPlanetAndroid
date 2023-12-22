//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.remote](../index.md)/[StudyPlanetApi](index.md)/[startDiscovering](start-discovering.md)

# startDiscovering

[androidJvm]\

@POST(value = &quot;v1/actions/discover&quot;)

abstract suspend fun [startDiscovering](start-discovering.md)(@Bodybody: [DiscoverActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-discover-action-dto/index.md)): Response&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)&gt;

Initiates the process of discovering a new planet.

#### Parameters

androidJvm

| | |
|---|---|
| body | The [DiscoverActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-discover-action-dto/index.md) containing information for the discovery action. |
