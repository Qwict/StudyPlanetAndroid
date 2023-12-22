//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.local.dao](../index.md)/[PlanetDao](index.md)

# PlanetDao

[androidJvm]\
interface [PlanetDao](index.md)

Data Access Object (DAO) for interacting with the local database regarding [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md). Defines methods to perform CRUD (Create, Read, Update, Delete) operations on planet entities.

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>abstract suspend fun [delete](delete.md)(planet: [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md))<br>Deletes a planet entity from the database. |
| [getPlanetByRemoteId](get-planet-by-remote-id.md) | [androidJvm]<br>abstract suspend fun [getPlanetByRemoteId](get-planet-by-remote-id.md)(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)<br>Retrieves a planet entity by its remote identifier from the database. |
| [getPlanetsByRemoteId](get-planets-by-remote-id.md) | [androidJvm]<br>abstract suspend fun [getPlanetsByRemoteId](get-planets-by-remote-id.md)(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt;<br>Retrieves all planet entities associated with a user's remote identifier from the database. |
| [insert](insert.md) | [androidJvm]<br>abstract suspend fun [insert](insert.md)(planet: [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md))<br>Inserts a single planet entity into the database. If a conflict arises, the operation is ignored. |
| [insertAll](insert-all.md) | [androidJvm]<br>abstract suspend fun [insertAll](insert-all.md)(planets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt;)<br>Inserts a list of planet entities into the database. If a conflict arises, the operation is ignored. |
| [update](update.md) | [androidJvm]<br>abstract suspend fun [update](update.md)(planet: [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md))<br>Updates an existing planet entity in the database. |
