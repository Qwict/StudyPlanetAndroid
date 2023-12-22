//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.local.database](../index.md)/[OfflineUsersRepositoryImpl](index.md)

# OfflineUsersRepositoryImpl

[androidJvm]\
class [OfflineUsersRepositoryImpl](index.md)(userDao: [UserDao](../../com.qwict.studyplanetandroid.data.local.dao/-user-dao/index.md)) : [OfflineUsersRepository](../-offline-users-repository/index.md)

## Constructors

| | |
|---|---|
| [OfflineUsersRepositoryImpl](-offline-users-repository-impl.md) | [androidJvm]<br>constructor(userDao: [UserDao](../../com.qwict.studyplanetandroid.data.local.dao/-user-dao/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>open suspend override fun [delete](delete.md)(user: [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)) |
| [getUserById](get-user-by-id.md) | [androidJvm]<br>open override fun [getUserById](get-user-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): Flow&lt;[UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)&gt; |
| [getUserByRemoteId](get-user-by-remote-id.md) | [androidJvm]<br>open suspend override fun [getUserByRemoteId](get-user-by-remote-id.md)(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md) |
| [insert](insert.md) | [androidJvm]<br>open suspend override fun [insert](insert.md)(user: [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)) |
| [insertAll](insert-all.md) | [androidJvm]<br>open suspend override fun [insertAll](insert-all.md)(users: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)&gt;) |
| [update](update.md) | [androidJvm]<br>open suspend override fun [update](update.md)(user: [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)) |
