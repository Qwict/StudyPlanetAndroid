//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.repository](../index.md)/[StudyPlanetRepositoryImpl](index.md)/[stopDiscovering](stop-discovering.md)

# stopDiscovering

[androidJvm]\
open suspend override fun [stopDiscovering](stop-discovering.md)(body: [DiscoverActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-discover-action-dto/index.md)): [PlanetDto](../../com.qwict.studyplanetandroid.data.remote.dto/-planet-dto/index.md)?

Stops the process of discovering a planet remotely and performs local database operations if successful.

#### Return

The [PlanetDto](../../com.qwict.studyplanetandroid.data.remote.dto/-planet-dto/index.md) representing the discovered planet, or null if the discovery was unsuccessful.

#### Parameters

androidJvm

| | |
|---|---|
| body | The [DiscoverActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-discover-action-dto/index.md) containing information for stopping the discovery action. |
