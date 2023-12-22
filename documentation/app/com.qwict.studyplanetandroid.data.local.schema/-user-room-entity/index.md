//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.local.schema](../index.md)/[UserRoomEntity](index.md)

# UserRoomEntity

[androidJvm]\
data class [UserRoomEntity](index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, var experience: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, var remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, var email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, var name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;)

Room Entity representing a user in the local database.

## Constructors

| | |
|---|---|
| [UserRoomEntity](-user-room-entity.md) | [androidJvm]<br>constructor(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, experience: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;) |

## Properties

| Name | Summary |
|---|---|
| [email](email.md) | [androidJvm]<br>var [email](email.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The email address of the user. |
| [experience](experience.md) | [androidJvm]<br>var [experience](experience.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The experience level of the user. |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0<br>The local database identifier for the user. |
| [name](name.md) | [androidJvm]<br>var [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the user. |
| [remoteId](remote-id.md) | [androidJvm]<br>var [remoteId](remote-id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The remote identifier of the user. |

## Functions

| Name | Summary |
|---|---|
| [toUser](../to-user.md) | [androidJvm]<br>fun [UserRoomEntity](index.md).[toUser](../to-user.md)(): [User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md)<br>Converts a [UserRoomEntity](index.md) into a [User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md) domain model. |
