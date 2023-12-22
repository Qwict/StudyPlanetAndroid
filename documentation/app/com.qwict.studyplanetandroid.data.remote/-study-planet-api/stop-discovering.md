//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.remote](../index.md)/[StudyPlanetApi](index.md)/[stopDiscovering](stop-discovering.md)

# stopDiscovering

[androidJvm]\

@PUT(value = &quot;v1/actions/discover&quot;)

abstract suspend fun [stopDiscovering](stop-discovering.md)(@Bodybody: [DiscoverActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-discover-action-dto/index.md)): Response&lt;[PlanetDto](../../com.qwict.studyplanetandroid.data.remote.dto/-planet-dto/index.md)?&gt;

Stops the process of discovering a planet.

#### Return

The [PlanetDto](../../com.qwict.studyplanetandroid.data.remote.dto/-planet-dto/index.md) representing the discovered planet, or null if the discovery was unsuccessful.

#### Parameters

androidJvm

| | |
|---|---|
| body | The [DiscoverActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-discover-action-dto/index.md) containing information for stopping the discovery action. |
