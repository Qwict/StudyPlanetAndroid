//[app](../../index.md)/[com.qwict.studyplanetandroid.presentation](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [SnackbarAppState](-snackbar-app-state/index.md) | [androidJvm]<br>class [SnackbarAppState](-snackbar-app-state/index.md)(val scaffoldState: [ScaffoldState](https://developer.android.com/reference/kotlin/androidx/compose/material/ScaffoldState.html), val snackbarScope: CoroutineScope, val navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html)) |
| [StudyPlanetScreens](-study-planet-screens/index.md) | [androidJvm]<br>enum [StudyPlanetScreens](-study-planet-screens/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[StudyPlanetScreens](-study-planet-screens/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [rememberSnackbarAppState](remember-snackbar-app-state.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [rememberSnackbarAppState](remember-snackbar-app-state.md)(scaffoldState: [ScaffoldState](https://developer.android.com/reference/kotlin/androidx/compose/material/ScaffoldState.html) = rememberScaffoldState(         snackbarHostState = remember {             SnackbarHostState()         },     ), navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html) = rememberNavController(), snackbarScope: CoroutineScope = rememberCoroutineScope()): [SnackbarAppState](-snackbar-app-state/index.md) |
| [sharedViewModel](shared-view-model.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>inline fun &lt;[T](shared-view-model.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)&gt; [NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html).[sharedViewModel](shared-view-model.md)(navController: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html)): [T](shared-view-model.md) |
| [StudyPlanetNavigation](-study-planet-navigation.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [StudyPlanetNavigation](-study-planet-navigation.md)(navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html))<br>Composable function defining the navigation structure for the StudyPlanet application. |
