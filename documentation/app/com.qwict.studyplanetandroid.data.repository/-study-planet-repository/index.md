//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.repository](../index.md)/[StudyPlanetRepository](index.md)

# StudyPlanetRepository

interface [StudyPlanetRepository](index.md)

#### Inheritors

| |
|---|
| [StudyPlanetRepositoryImpl](../-study-planet-repository-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [authenticate](authenticate.md) | [androidJvm]<br>abstract suspend fun [authenticate](authenticate.md)(token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md) |
| [getHealth](get-health.md) | [androidJvm]<br>abstract suspend fun [getHealth](get-health.md)(): [HealthDto](../../com.qwict.studyplanetandroid.data.remote.dto/-health-dto/index.md) |
| [getPlanetsByRemoteId](get-planets-by-remote-id.md) | [androidJvm]<br>abstract suspend fun [getPlanetsByRemoteId](get-planets-by-remote-id.md)(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt; |
| [getUserByRemoteId](get-user-by-remote-id.md) | [androidJvm]<br>abstract suspend fun [getUserByRemoteId](get-user-by-remote-id.md)(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md) |
| [insertPlanet](insert-planet.md) | [androidJvm]<br>abstract suspend fun [insertPlanet](insert-planet.md)(planet: [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)) |
| [insertPlanets](insert-planets.md) | [androidJvm]<br>abstract suspend fun [insertPlanets](insert-planets.md)(planets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt;) |
| [login](login.md) | [androidJvm]<br>abstract suspend fun [login](login.md)(body: [LoginDto](../../com.qwict.studyplanetandroid.data.remote.dto/-login-dto/index.md)): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md) |
| [register](register.md) | [androidJvm]<br>abstract suspend fun [register](register.md)(body: [RegisterDto](../../com.qwict.studyplanetandroid.data.remote.dto/-register-dto/index.md)): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md) |
| [registerLocalUser](register-local-user.md) | [androidJvm]<br>abstract suspend fun [registerLocalUser](register-local-user.md)(): [User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md) |
| [startDiscovering](start-discovering.md) | [androidJvm]<br>abstract suspend fun [startDiscovering](start-discovering.md)(body: [DiscoverActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-discover-action-dto/index.md)): Response&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)&gt; |
| [startExploring](start-exploring.md) | [androidJvm]<br>abstract suspend fun [startExploring](start-exploring.md)(body: [ExploreActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-explore-action-dto/index.md)): Response&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)&gt; |
| [stopDiscovering](stop-discovering.md) | [androidJvm]<br>abstract suspend fun [stopDiscovering](stop-discovering.md)(body: [DiscoverActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-discover-action-dto/index.md)): [PlanetDto](../../com.qwict.studyplanetandroid.data.remote.dto/-planet-dto/index.md)? |
| [stopExploring](stop-exploring.md) | [androidJvm]<br>abstract suspend fun [stopExploring](stop-exploring.md)(body: [ExploreActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-explore-action-dto/index.md)): [UserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-user-dto/index.md) |
