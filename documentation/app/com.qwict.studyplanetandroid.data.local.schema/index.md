//[app](../../index.md)/[com.qwict.studyplanetandroid.data.local.schema](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [DatabaseUserWithPlanets](-database-user-with-planets/index.md) | [androidJvm]<br>data class [DatabaseUserWithPlanets](-database-user-with-planets/index.md)(val user: [UserRoomEntity](-user-room-entity/index.md), val planets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](-planet-room-entity/index.md)&gt;)<br>Data class representing a user with associated planets in the local database. |
| [PlanetRoomEntity](-planet-room-entity/index.md) | [androidJvm]<br>data class [PlanetRoomEntity](-planet-room-entity/index.md)(val remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val userOwnerId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Room Entity representing a planet in the local database. |
| [UserRoomEntity](-user-room-entity/index.md) | [androidJvm]<br>data class [UserRoomEntity](-user-room-entity/index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, var experience: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, var remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, var email: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, var name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;)<br>Room Entity representing a user in the local database. |

## Functions

| Name | Summary |
|---|---|
| [asDomainModel](as-domain-model.md) | [androidJvm]<br>fun [DatabaseUserWithPlanets](-database-user-with-planets/index.md).[asDomainModel](as-domain-model.md)(): [User](../com.qwict.studyplanetandroid.domain.model/-user/index.md)<br>Converts a [DatabaseUserWithPlanets](-database-user-with-planets/index.md) into a [User](../com.qwict.studyplanetandroid.domain.model/-user/index.md) domain model. |
| [populatePlanets](populate-planets.md) | [androidJvm]<br>fun [populatePlanets](populate-planets.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](-planet-room-entity/index.md)&gt;<br>Populates a list of default [PlanetRoomEntity](-planet-room-entity/index.md) objects. |
| [toPlanet](to-planet.md) | [androidJvm]<br>fun [PlanetRoomEntity](-planet-room-entity/index.md).[toPlanet](to-planet.md)(): [Planet](../com.qwict.studyplanetandroid.domain.model/-planet/index.md)<br>Converts a [PlanetRoomEntity](-planet-room-entity/index.md) into a [Planet](../com.qwict.studyplanetandroid.domain.model/-planet/index.md) domain model. |
| [toUser](to-user.md) | [androidJvm]<br>fun [UserRoomEntity](-user-room-entity/index.md).[toUser](to-user.md)(): [User](../com.qwict.studyplanetandroid.domain.model/-user/index.md)<br>Converts a [UserRoomEntity](-user-room-entity/index.md) into a [User](../com.qwict.studyplanetandroid.domain.model/-user/index.md) domain model. |
