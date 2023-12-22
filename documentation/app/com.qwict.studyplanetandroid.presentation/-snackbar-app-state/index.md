//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation](../index.md)/[SnackbarAppState](index.md)

# SnackbarAppState

[androidJvm]\
class [SnackbarAppState](index.md)(val scaffoldState: [ScaffoldState](https://developer.android.com/reference/kotlin/androidx/compose/material/ScaffoldState.html), val snackbarScope: CoroutineScope, val navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html))

## Constructors

| | |
|---|---|
| [SnackbarAppState](-snackbar-app-state.md) | [androidJvm]<br>constructor(scaffoldState: [ScaffoldState](https://developer.android.com/reference/kotlin/androidx/compose/material/ScaffoldState.html), snackbarScope: CoroutineScope, navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html)) |

## Properties

| Name | Summary |
|---|---|
| [navController](nav-controller.md) | [androidJvm]<br>val [navController](nav-controller.md): [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html) |
| [scaffoldState](scaffold-state.md) | [androidJvm]<br>val [scaffoldState](scaffold-state.md): [ScaffoldState](https://developer.android.com/reference/kotlin/androidx/compose/material/ScaffoldState.html) |
| [snackbarScope](snackbar-scope.md) | [androidJvm]<br>val [snackbarScope](snackbar-scope.md): CoroutineScope |

## Functions

| Name | Summary |
|---|---|
| [showSnackbar](show-snackbar.md) | [androidJvm]<br>fun [showSnackbar](show-snackbar.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), duration: [SnackbarDuration](https://developer.android.com/reference/kotlin/androidx/compose/material/SnackbarDuration.html) = SnackbarDuration.Short) |
