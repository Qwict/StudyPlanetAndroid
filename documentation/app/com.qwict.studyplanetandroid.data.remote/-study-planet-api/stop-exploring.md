//[app](../../../index.md)/[com.qwict.studyplanetandroid.data.remote](../index.md)/[StudyPlanetApi](index.md)/[stopExploring](stop-exploring.md)

# stopExploring

[androidJvm]\

@PUT(value = &quot;v1/actions/explore&quot;)

abstract suspend fun [stopExploring](stop-exploring.md)(@Bodybody: [ExploreActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-explore-action-dto/index.md)): [UserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-user-dto/index.md)

Stops the process of exploring a planet.

#### Return

The [UserDto](../../com.qwict.studyplanetandroid.data.remote.dto/-user-dto/index.md) representing the user after exploration.

#### Parameters

androidJvm

| | |
|---|---|
| body | The [ExploreActionDto](../../com.qwict.studyplanetandroid.data.remote.dto/-explore-action-dto/index.md) containing information for stopping the exploration action. |
