//[app](../../index.md)/[com.qwict.studyplanetandroid.data.remote.dto](index.md)/[asDatabaseModel](as-database-model.md)

# asDatabaseModel

[androidJvm]\
fun [PlanetDto](-planet-dto/index.md).[asDatabaseModel](as-database-model.md)(userId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [PlanetRoomEntity](../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)

Converts the [PlanetDto](-planet-dto/index.md) to a [PlanetRoomEntity](../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md) for local database storage.

#### Return

The [PlanetRoomEntity](../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md) representation of the planet.

#### Parameters

androidJvm

| | |
|---|---|
| userId | The unique identifier of the user associated with the planet. |

[androidJvm]\
fun [UserDto](-user-dto/index.md).[asDatabaseModel](as-database-model.md)(): [UserRoomEntity](../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)

Converts the [UserDto](-user-dto/index.md) to a [UserRoomEntity](../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md) for local database storage.

#### Return

The [UserRoomEntity](../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md) representation of the user.
