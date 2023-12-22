//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels.states](../index.md)/[StudyPlanetUiState](index.md)

# StudyPlanetUiState

[androidJvm]\
data class [StudyPlanetUiState](index.md)(var user: [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md) = UserRoomEntity(), val snackBarHostState: [SnackbarHostState](https://developer.android.com/reference/kotlin/androidx/compose/material3/SnackbarHostState.html) = SnackbarHostState(), var appJustLaunched: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; = mutableStateOf(true), var planets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt; = emptyList())

## Constructors

| | |
|---|---|
| [StudyPlanetUiState](-study-planet-ui-state.md) | [androidJvm]<br>constructor(user: [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md) = UserRoomEntity(), snackBarHostState: [SnackbarHostState](https://developer.android.com/reference/kotlin/androidx/compose/material3/SnackbarHostState.html) = SnackbarHostState(), appJustLaunched: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; = mutableStateOf(true), planets: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt; = emptyList()) |

## Properties

| Name | Summary |
|---|---|
| [appJustLaunched](app-just-launched.md) | [androidJvm]<br>var [appJustLaunched](app-just-launched.md): [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
| [planets](planets.md) | [androidJvm]<br>var [planets](planets.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PlanetRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-planet-room-entity/index.md)&gt; |
| [snackBarHostState](snack-bar-host-state.md) | [androidJvm]<br>val [snackBarHostState](snack-bar-host-state.md): [SnackbarHostState](https://developer.android.com/reference/kotlin/androidx/compose/material3/SnackbarHostState.html) |
| [user](user.md) | [androidJvm]<br>var [user](user.md): [UserRoomEntity](../../com.qwict.studyplanetandroid.data.local.schema/-user-room-entity/index.md) |
