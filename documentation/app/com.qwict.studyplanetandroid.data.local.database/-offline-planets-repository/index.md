//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.local.database](../index.md)/[OfflinePlanetsRepository](index.md)

# OfflinePlanetsRepository

interface [OfflinePlanetsRepository](index.md)

#### Inheritors

| |
|---|
| [OfflinePlanetsRepositoryImpl](../-offline-planets-repository-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>abstract suspend fun [delete](delete.md)(planet: [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)) |
| [getPlanetById](get-planet-by-id.md) | [androidJvm]<br>abstract suspend fun [getPlanetById](get-planet-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md) |
| [getPlanetsByRemoteId](get-planets-by-remote-id.md) | [androidJvm]<br>abstract suspend fun [getPlanetsByRemoteId](get-planets-by-remote-id.md)(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt; |
| [insert](insert.md) | [androidJvm]<br>abstract suspend fun [insert](insert.md)(planet: [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)) |
| [insertAll](insert-all.md) | [androidJvm]<br>abstract suspend fun [insertAll](insert-all.md)(planets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt;) |
| [update](update.md) | [androidJvm]<br>abstract suspend fun [update](update.md)(planet: [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)) |
