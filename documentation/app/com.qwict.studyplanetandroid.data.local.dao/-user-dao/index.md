//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.local.dao](../index.md)/[UserDao](index.md)

# UserDao

[androidJvm]\
interface [UserDao](index.md)

Data Access Object (DAO) for interacting with the local database regarding [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md). Defines methods to perform CRUD (Create, Read, Update, Delete) operations on user entities.

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>abstract suspend fun [delete](delete.md)(user: [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md))<br>Deletes a user entity from the database. |
| [getFlowUserById](get-flow-user-by-id.md) | [androidJvm]<br>abstract fun [getFlowUserById](get-flow-user-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): Flow&lt;[UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)&gt;<br>Retrieves a flow of a user entity by its identifier from the database. |
| [getUserById](get-user-by-id.md) | [androidJvm]<br>abstract fun [getUserById](get-user-by-id.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)<br>Retrieves a user entity by its identifier from the database. |
| [getUserByRemoteId](get-user-by-remote-id.md) | [androidJvm]<br>abstract suspend fun [getUserByRemoteId](get-user-by-remote-id.md)(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)<br>Retrieves a user entity by its remote identifier from the database. |
| [getUsersWithPlanets](get-users-with-planets.md) | [androidJvm]<br>abstract fun [getUsersWithPlanets](get-users-with-planets.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DatabaseUserWithPlanets](../../com.qwict.studyplanetandroid.data.local.schema/-database-user-with-planets/index.md)&gt;<br>Retrieves a list of user entities along with associated planet entities from the database. |
| [insert](insert.md) | [androidJvm]<br>abstract suspend fun [insert](insert.md)(user: [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md))<br>Inserts a single user entity into the database. If a conflict arises, the existing entry is replaced. |
| [insertAll](insert-all.md) | [androidJvm]<br>abstract suspend fun [insertAll](insert-all.md)(users: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)&gt;)<br>Inserts a list of user entities into the database. If a conflict arises, the existing entries are replaced. |
| [update](update.md) | [androidJvm]<br>abstract suspend fun [update](update.md)(user: [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md))<br>Updates an existing user entity in the database. |
