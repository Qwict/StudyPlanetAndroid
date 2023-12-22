//[app](../../index.md)/[com.qwict.studyplanetandroid.data.remote.dto](index.md)/[toDatabaseUser](to-database-user.md)

# toDatabaseUser

[androidJvm]\
fun [AuthenticatedUserDto](-authenticated-user-dto/index.md).[toDatabaseUser](to-database-user.md)(): [UserRoomEntity](../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)

Converts the [AuthenticatedUserDto](-authenticated-user-dto/index.md) to a [UserRoomEntity](../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md) for local database storage. This function might be useful when a user registers with no discovered planets yet.

#### Return

The [UserRoomEntity](../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md) representation of the authenticated user.
