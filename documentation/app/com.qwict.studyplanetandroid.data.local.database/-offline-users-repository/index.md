//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.local.database](../index.md)/[OfflineUsersRepository](index.md)

# OfflineUsersRepository

interface [OfflineUsersRepository](index.md)

#### Inheritors

| |
|---|
| [OfflineUsersRepositoryImpl](../-offline-users-repository-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>abstract suspend fun [delete](delete.md)(user: [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)) |
| [getUserById](get-user-by-id.md) | [androidJvm]<br>abstract fun [getUserById](get-user-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): Flow&lt;[UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)&gt; |
| [getUserByRemoteId](get-user-by-remote-id.md) | [androidJvm]<br>abstract suspend fun [getUserByRemoteId](get-user-by-remote-id.md)(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md) |
| [insert](insert.md) | [androidJvm]<br>abstract suspend fun [insert](insert.md)(user: [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)) |
| [insertAll](insert-all.md) | [androidJvm]<br>abstract suspend fun [insertAll](insert-all.md)(users: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)&gt;) |
| [update](update.md) | [androidJvm]<br>abstract suspend fun [update](update.md)(user: [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)) |
