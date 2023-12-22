//[app](../../index.md)/[com.qwict.studyplanetandroid.presentation.screens](index.md)/[ExplorerScreen](-explorer-screen.md)

# ExplorerScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ExplorerScreen](-explorer-screen.md)(navigateBackToMainScreen: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, navigateBackToDiscoveredPlanetsScreen: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, studyViewModel: [StudyViewModel](../com.qwict.studyplanetandroid.presentation.viewmodels/-study-view-model/index.md) = hiltViewModel(), state: [StudyState](../com.qwict.studyplanetandroid.presentation.viewmodels.states/-study-state/index.md) = studyViewModel.state, isDiscovering: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), selectedPlanet: [Planet](../com.qwict.studyplanetandroid.domain.model/-planet/index.md), selectedTimeInMinutes: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html))
