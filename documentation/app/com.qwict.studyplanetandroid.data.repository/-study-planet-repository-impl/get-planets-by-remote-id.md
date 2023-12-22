//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.repository](../index.md)/[StudyPlanetRepositoryImpl](index.md)/[getPlanetsByRemoteId](get-planets-by-remote-id.md)

# getPlanetsByRemoteId

[androidJvm]\
open suspend override fun [getPlanetsByRemoteId](get-planets-by-remote-id.md)(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt;

Retrieves a list of planets associated with a user's remote ID from the local database.

#### Return

A list of [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md) representing the planets associated with the user.

#### Parameters

androidJvm

| | |
|---|---|
| remoteId | The remote ID of the user. |
