//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.local.dao](../index.md)/[PlanetDao](index.md)/[insertAll](insert-all.md)

# insertAll

[androidJvm]\
abstract suspend fun [insertAll](insert-all.md)(planets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt;)

Inserts a list of planet entities into the database. If a conflict arises, the operation is ignored.

#### Parameters

androidJvm

| | |
|---|---|
| planets | The list of planet entities to be inserted. |
