//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.local.database](../index.md)/[OfflinePlanetsRepositoryImpl](index.md)

# OfflinePlanetsRepositoryImpl

[androidJvm]\
class [OfflinePlanetsRepositoryImpl](index.md)(planetDao: [PlanetDao](../../com.qwict.studyplanetandroid.data.local.dao/-planet-dao/index.md)) : [OfflinePlanetsRepository](../-offline-planets-repository/index.md)

## Constructors

| | |
|---|---|
| [OfflinePlanetsRepositoryImpl](-offline-planets-repository-impl.md) | [androidJvm]<br>constructor(planetDao: [PlanetDao](../../com.qwict.studyplanetandroid.data.local.dao/-planet-dao/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>open suspend override fun [delete](delete.md)(planet: [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)) |
| [getPlanetById](get-planet-by-id.md) | [androidJvm]<br>open suspend override fun [getPlanetById](get-planet-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md) |
| [getPlanetsByRemoteId](get-planets-by-remote-id.md) | [androidJvm]<br>open suspend override fun [getPlanetsByRemoteId](get-planets-by-remote-id.md)(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt; |
| [insert](insert.md) | [androidJvm]<br>open suspend override fun [insert](insert.md)(planet: [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)) |
| [insertAll](insert-all.md) | [androidJvm]<br>open suspend override fun [insertAll](insert-all.md)(planets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt;) |
| [update](update.md) | [androidJvm]<br>open suspend override fun [update](update.md)(planet: [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)) |
