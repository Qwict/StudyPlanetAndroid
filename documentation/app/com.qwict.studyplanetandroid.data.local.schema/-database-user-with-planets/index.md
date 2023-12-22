//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.local.schema](../index.md)/[DatabaseUserWithPlanets](index.md)

# DatabaseUserWithPlanets

[androidJvm]\
data class [DatabaseUserWithPlanets](index.md)(val user: [UserRoomEntity](../-user-room-entity/index.md), val planets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../-planet-room-entity/index.md)&gt;)

Data class representing a user with associated planets in the local database.

## Constructors

| | |
|---|---|
| [DatabaseUserWithPlanets](-database-user-with-planets.md) | [androidJvm]<br>constructor(user: [UserRoomEntity](../-user-room-entity/index.md), planets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../-planet-room-entity/index.md)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [planets](planets.md) | [androidJvm]<br>val [planets](planets.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../-planet-room-entity/index.md)&gt;<br>The list of planets associated with the user. |
| [user](user.md) | [androidJvm]<br>val [user](user.md): [UserRoomEntity](../-user-room-entity/index.md)<br>The user information. |

## Functions

| Name | Summary |
|---|---|
| [asDomainModel](../as-domain-model.md) | [androidJvm]<br>fun [DatabaseUserWithPlanets](index.md).[asDomainModel](../as-domain-model.md)(): [User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md)<br>Converts a [DatabaseUserWithPlanets](index.md) into a [User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md) domain model. |
