//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.repository](../index.md)/[StudyPlanetRepositoryImpl](index.md)

# StudyPlanetRepositoryImpl

class [StudyPlanetRepositoryImpl](index.md)@Injectconstructor(api: [StudyPlanetApi](../../com.qwict.studyplanetandroid.data.remote/-study-planet-api/index.md), userDatabase: [OfflineUsersRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-users-repository/index.md), planetDatabase: [OfflinePlanetsRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-planets-repository/index.md)) : [StudyPlanetRepository](../-study-planet-repository/index.md)

Implementation of the [StudyPlanetRepository](../-study-planet-repository/index.md) interface that communicates with both local and remote data sources. Application of facade pattern to abstract away the implementation details of the data sources.

#### Parameters

androidJvm

| | |
|---|---|
| api | The [StudyPlanetApi](../../com.qwict.studyplanetandroid.data.remote/-study-planet-api/index.md) instance for making remote API calls. |
| userDatabase | The [OfflineUsersRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-users-repository/index.md) for local user-related database operations. |
| planetDatabase | The [OfflinePlanetsRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-planets-repository/index.md) for local planet-related database operations. |

## Constructors

| | |
|---|---|
| [StudyPlanetRepositoryImpl](-study-planet-repository-impl.md) | [androidJvm]<br>@Inject<br>constructor(api: [StudyPlanetApi](../../com.qwict.studyplanetandroid.data.remote/-study-planet-api/index.md), userDatabase: [OfflineUsersRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-users-repository/index.md), planetDatabase: [OfflinePlanetsRepository](../../com.qwict.studyplanetandroid.data.local.database/-offline-planets-repository/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [authenticate](authenticate.md) | [androidJvm]<br>open suspend override fun [authenticate](authenticate.md)(token: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md)<br>Authenticates the current user remotely and performs local database operations to store the user and associated planets. |
| [getHealth](get-health.md) | [androidJvm]<br>open suspend override fun [getHealth](get-health.md)(): [HealthDto](../../com.qwict.studyplanetandroid.data.remote.dto/-health-dto/index.md)<br>Retrieves the health information of the Study Planet server. |
| [getPlanetsByRemoteId](get-planets-by-remote-id.md) | [androidJvm]<br>open suspend override fun [getPlanetsByRemoteId](get-planets-by-remote-id.md)(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt;<br>Retrieves a list of planets associated with a user's remote ID from the local database. |
| [getUserByRemoteId](get-user-by-remote-id.md) | [androidJvm]<br>open suspend override fun [getUserByRemoteId](get-user-by-remote-id.md)(remoteId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md)<br>Retrieves a user from the local database based on their remote ID. |
| [insertPlanet](insert-planet.md) | [androidJvm]<br>open suspend override fun [insertPlanet](insert-planet.md)(planet: [PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md))<br>Inserts a single planet into the local database. |
| [insertPlanets](insert-planets.md) | [androidJvm]<br>open suspend override fun [insertPlanets](insert-planets.md)(planets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt;)<br>Inserts a list of planets into the local database. |
| [login](login.md) | [androidJvm]<br>open suspend override fun [login](login.md)(body: [LoginDto](../../com.qwict.studyplanetandroid.data.remote.dto/-login-dto/index.md)): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md)<br>Logs in a user remotely and performs local database operations to store the user and associated planets. |
| [register](register.md) | [androidJvm]<br>open suspend override fun [register](register.md)(body: [RegisterDto](../../com.qwict.studyplanetandroid.data.remote.dto/-register-dto/index.md)): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md)<br>Registers a new user remotely and performs local database operations to store the user and associated planets. |
| [registerLocalUser](register-local-user.md) | [androidJvm]<br>open suspend override fun [registerLocalUser](register-local-user.md)(): [User](../../com.qwict.studyplanetandroid.domain.model/-user/index.md)<br>Placeholder method for registering a local user. Not implemented. |
| [startDiscovering](start-discovering.md) | [androidJvm]<br>open suspend override fun [startDiscovering](start-discovering.md)(body: [DiscoverActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-discover-action-dto/index.md)): Response&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)&gt;<br>Initiates the process of discovering a new planet remotely. |
| [startExploring](start-exploring.md) | [androidJvm]<br>open suspend override fun [startExploring](start-exploring.md)(body: [ExploreActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-explore-action-dto/index.md)): Response&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)&gt;<br>Initiates the process of exploring a planet remotely. |
| [stopDiscovering](stop-discovering.md) | [androidJvm]<br>open suspend override fun [stopDiscovering](stop-discovering.md)(body: [DiscoverActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-discover-action-dto/index.md)): [PlanetDto](../../com.qwict.studyplanetandroid.data.remote.dto/-planet-dto/index.md)?<br>Stops the process of discovering a planet remotely and performs local database operations if successful. |
| [stopExploring](stop-exploring.md) | [androidJvm]<br>open suspend override fun [stopExploring](stop-exploring.md)(body: [ExploreActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-explore-action-dto/index.md)): [UserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-user-dto/index.md)<br>Stops the process of exploring a planet remotely. |
