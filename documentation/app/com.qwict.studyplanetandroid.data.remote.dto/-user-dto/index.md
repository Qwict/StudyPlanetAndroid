//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.remote.dto](../index.md)/[UserDto](index.md)

# UserDto

[androidJvm]\
@JsonClass(generateAdapter = true)

data class [UserDto](index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val experience: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val discoveredPlanets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetDto](../-planet-dto/index.md)&gt;)

Data transfer object (DTO) representing a user received from the remote API.

## Constructors

| | |
|---|---|
| [UserDto](-user-dto.md) | [androidJvm]<br>constructor(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), experience: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), discoveredPlanets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetDto](../-planet-dto/index.md)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [discoveredPlanets](discovered-planets.md) | [androidJvm]<br>val [discoveredPlanets](discovered-planets.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetDto](../-planet-dto/index.md)&gt;<br>The list of planets discovered by the user. |
| [email](email.md) | [androidJvm]<br>val [email](email.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The email address of the user. |
| [experience](experience.md) | [androidJvm]<br>val [experience](experience.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The experience level of the user. |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The unique identifier of the user. |
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the user. |

## Functions

| Name | Summary |
|---|---|
| [asDatabaseModel](../as-database-model.md) | [androidJvm]<br>fun [UserDto](index.md).[asDatabaseModel](../as-database-model.md)(): [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)<br>Converts the [UserDto](index.md) to a [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md) for local database storage. |
