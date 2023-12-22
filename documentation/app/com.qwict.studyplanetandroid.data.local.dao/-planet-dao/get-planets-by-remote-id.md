//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.local.dao](../index.md)/[PlanetDao](index.md)/[getPlanetsByRemoteId](get-planets-by-remote-id.md)

# getPlanetsByRemoteId

[androidJvm]\
abstract suspend fun [getPlanetsByRemoteId](get-planets-by-remote-id.md)(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt;

Retrieves all planet entities associated with a user's remote identifier from the database.

#### Return

A list of planet entities associated with the specified user.

#### Parameters

androidJvm

| | |
|---|---|
| remoteId | The remote identifier of the user. |
