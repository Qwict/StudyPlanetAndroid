//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.remote](../index.md)/[StudyPlanetApi](index.md)

# StudyPlanetApi

[androidJvm]\
interface [StudyPlanetApi](index.md)

Retrofit API interface for Study Planet, defining various HTTP methods for interacting with the Study Planet server.

## Functions

| Name | Summary |
|---|---|
| [authenticate](authenticate.md) | [androidJvm]<br>@GET(value = &quot;v1/users&quot;)<br>abstract suspend fun [authenticate](authenticate.md)(): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md)<br>Authenticates the current user. |
| [getHealth](get-health.md) | [androidJvm]<br>@GET(value = &quot;v1/health/version&quot;)<br>abstract suspend fun [getHealth](get-health.md)(): [HealthDto](../../com.qwict.studyplanetandroid.data.remote.dto/-health-dto/index.md)<br>Checks the health and version of the Study Planet server. |
| [login](login.md) | [androidJvm]<br>@POST(value = &quot;v1/users/login&quot;)<br>abstract suspend fun [login](login.md)(@Bodybody: [LoginDto](../../com.qwict.studyplanetandroid.data.remote.dto/-login-dto/index.md)): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md)<br>Attempts to log in a user with the provided [LoginDto](../../com.qwict.studyplanetandroid.data.remote.dto/-login-dto/index.md). |
| [register](register.md) | [androidJvm]<br>@POST(value = &quot;v1/users/register&quot;)<br>abstract suspend fun [register](register.md)(@Bodybody: [RegisterDto](../../com.qwict.studyplanetandroid.data.remote.dto/-register-dto/index.md)): [AuthenticatedUserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-authenticated-user-dto/index.md)<br>Registers a new user with the provided [RegisterDto](../../com.qwict.studyplanetandroid.data.remote.dto/-register-dto/index.md). |
| [startDiscovering](start-discovering.md) | [androidJvm]<br>@POST(value = &quot;v1/actions/discover&quot;)<br>abstract suspend fun [startDiscovering](start-discovering.md)(@Bodybody: [DiscoverActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-discover-action-dto/index.md)): Response&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)&gt;<br>Initiates the process of discovering a new planet. |
| [startExploring](start-exploring.md) | [androidJvm]<br>@POST(value = &quot;v1/actions/explore&quot;)<br>abstract suspend fun [startExploring](start-exploring.md)(@Bodybody: [ExploreActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-explore-action-dto/index.md)): Response&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)&gt;<br>Initiates the process of exploring a planet. |
| [stopDiscovering](stop-discovering.md) | [androidJvm]<br>@PUT(value = &quot;v1/actions/discover&quot;)<br>abstract suspend fun [stopDiscovering](stop-discovering.md)(@Bodybody: [DiscoverActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-discover-action-dto/index.md)): Response&lt;[PlanetDto](../../com.qwict.studyplanetandroid.data.remote.dto/-planet-dto/index.md)?&gt;<br>Stops the process of discovering a planet. |
| [stopExploring](stop-exploring.md) | [androidJvm]<br>@PUT(value = &quot;v1/actions/explore&quot;)<br>abstract suspend fun [stopExploring](stop-exploring.md)(@Bodybody: [ExploreActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-explore-action-dto/index.md)): [UserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-user-dto/index.md)<br>Stops the process of exploring a planet. |
