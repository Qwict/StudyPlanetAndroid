//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.remote.dto](../index.md)/[PlanetDto](index.md)

# PlanetDto

[androidJvm]\
@JsonClass(generateAdapter = true)

data class [PlanetDto](index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Data transfer object (DTO) representing a planet received from the remote API.

## Constructors

| | |
|---|---|
| [PlanetDto](-planet-dto.md) | [androidJvm]<br>constructor(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The unique identifier of the planet. |
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the planet. |

## Functions

| Name | Summary |
|---|---|
| [asDatabaseModel](../as-database-model.md) | [androidJvm]<br>fun [PlanetDto](index.md).[asDatabaseModel](../as-database-model.md)(userId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)<br>Converts the [PlanetDto](index.md) to a [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md) for local database storage. |
| [asDomainModel](../as-domain-model.md) | [androidJvm]<br>fun [PlanetDto](index.md).[asDomainModel](../as-domain-model.md)(): [Planet](../../com.qwict.studyplanetandroid.domain.model/-planet/index.md)<br>Converts the [PlanetDto](index.md) to a [Planet](../../com.qwict.studyplanetandroid.domain.model/-planet/index.md) domain model for application use. |
