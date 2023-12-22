//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.local.dao](../index.md)/[UserDao](index.md)/[insertAll](insert-all.md)

# insertAll

[androidJvm]\
abstract suspend fun [insertAll](insert-all.md)(users: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)&gt;)

Inserts a list of user entities into the database. If a conflict arises, the existing entries are replaced.

#### Parameters

androidJvm

| | |
|---|---|
| users | The list of user entities to be inserted. |
