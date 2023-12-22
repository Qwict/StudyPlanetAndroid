//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.remote.dto](../index.md)/[AuthenticatedUserDto](index.md)

# AuthenticatedUserDto

[androidJvm]\
@JsonClass(generateAdapter = true)

data class [AuthenticatedUserDto](index.md)(val token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val user: [UserDto](../-user-dto/index.md), val validated: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))

Data transfer object (DTO) representing an authenticated user received from the remote API.

## Constructors

| | |
|---|---|
| [AuthenticatedUserDto](-authenticated-user-dto.md) | [androidJvm]<br>constructor(token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), user: [UserDto](../-user-dto/index.md), validated: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [token](token.md) | [androidJvm]<br>val [token](token.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The authentication token associated with the user. |
| [user](user.md) | [androidJvm]<br>val [user](user.md): [UserDto](../-user-dto/index.md)<br>The detailed information about the authenticated user. |
| [validated](validated.md) | [androidJvm]<br>val [validated](validated.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Indicates whether the user has been validated. |

## Functions

| Name | Summary |
|---|---|
| [asDomainModel](../as-domain-model.md) | [androidJvm]<br>fun [AuthenticatedUserDto](index.md).[asDomainModel](../as-domain-model.md)(): [User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md)<br>Converts the [AuthenticatedUserDto](index.md) to a [User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md) domain model for application use. |
| [toDatabaseUser](../to-database-user.md) | [androidJvm]<br>fun [AuthenticatedUserDto](index.md).[toDatabaseUser](../to-database-user.md)(): [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)<br>Converts the [AuthenticatedUserDto](index.md) to a [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md) for local database storage. This function might be useful when a user registers with no discovered planets yet. |
| [toDatabaseUserWithPlanets](../to-database-user-with-planets.md) | [androidJvm]<br>fun [AuthenticatedUserDto](index.md).[toDatabaseUserWithPlanets](../to-database-user-with-planets.md)(): [DatabaseUserWithPlanets](../../com.qwict.studyplanetandroid.data.local.schema/-database-user-with-planets/index.md)<br>Converts the [AuthenticatedUserDto](index.md) to a [DatabaseUserWithPlanets](../../com.qwict.studyplanetandroid.data.local.schema/-database-user-with-planets/index.md) for local database storage. |
