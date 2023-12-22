//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.local.schema](../index.md)/[PlanetRoomEntity](index.md)

# PlanetRoomEntity

[androidJvm]\
data class [PlanetRoomEntity](index.md)(val remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val userOwnerId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Room Entity representing a planet in the local database.

## Constructors

| | |
|---|---|
| [PlanetRoomEntity](-planet-room-entity.md) | [androidJvm]<br>constructor(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), userOwnerId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the planet. |
| [remoteId](remote-id.md) | [androidJvm]<br>val [remoteId](remote-id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0<br>The remote identifier of the planet. |
| [userOwnerId](user-owner-id.md) | [androidJvm]<br>val [userOwnerId](user-owner-id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The remote identifier of the user to whom the planet belongs. |

## Functions

| Name | Summary |
|---|---|
| [toPlanet](../to-planet.md) | [androidJvm]<br>fun [PlanetRoomEntity](index.md).[toPlanet](../to-planet.md)(): [Planet](../../com.qwict.studyplanetandroid.domain.model/-planet/index.md)<br>Converts a [PlanetRoomEntity](index.md) into a [Planet](../../com.qwict.studyplanetandroid.domain.model/-planet/index.md) domain model. |
