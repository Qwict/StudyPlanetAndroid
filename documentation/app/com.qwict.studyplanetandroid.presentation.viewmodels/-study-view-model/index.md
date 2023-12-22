//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels](../index.md)/[StudyViewModel](index.md)

# StudyViewModel

[androidJvm]\
class [StudyViewModel](index.md)@Injectconstructor(startDiscoveringUseCase: [StartDiscoveringUseCase](../../com.qwict.studyplanetandroid.domain.use_case.actions/-start-discovering-use-case/index.md), stopDiscoveringUseCase: [StopDiscoveringUseCase](../../com.qwict.studyplanetandroid.domain.use_case.actions/-stop-discovering-use-case/index.md), startExploringUseCase: [StartExploringUseCase](../../com.qwict.studyplanetandroid.domain.use_case.actions/-start-exploring-use-case/index.md), stopExploringUseCase: [StopExploringUseCase](../../com.qwict.studyplanetandroid.domain.use_case.actions/-stop-exploring-use-case/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

## Constructors

| | |
|---|---|
| [StudyViewModel](-study-view-model.md) | [androidJvm]<br>@Inject<br>constructor(startDiscoveringUseCase: [StartDiscoveringUseCase](../../com.qwict.studyplanetandroid.domain.use_case.actions/-start-discovering-use-case/index.md), stopDiscoveringUseCase: [StopDiscoveringUseCase](../../com.qwict.studyplanetandroid.domain.use_case.actions/-stop-discovering-use-case/index.md), startExploringUseCase: [StartExploringUseCase](../../com.qwict.studyplanetandroid.domain.use_case.actions/-start-exploring-use-case/index.md), stopExploringUseCase: [StopExploringUseCase](../../com.qwict.studyplanetandroid.domain.use_case.actions/-stop-exploring-use-case/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [state](state.md) | [androidJvm]<br>var [state](state.md): [StudyState](../../com.qwict.studyplanetandroid.presentation.viewmodels.states/-study-state/index.md) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-user-view-model/index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-user-view-model/index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [closeBackAlertDialog](close-back-alert-dialog.md) | [androidJvm]<br>fun [closeBackAlertDialog](close-back-alert-dialog.md)() |
| [openOnBackAlertDialog](open-on-back-alert-dialog.md) | [androidJvm]<br>fun [openOnBackAlertDialog](open-on-back-alert-dialog.md)() |
| [resetAction](reset-action.md) | [androidJvm]<br>fun [resetAction](reset-action.md)()<br>Resets the study state by setting relevant properties to their initial values. |
| [startCountDown](start-count-down.md) | [androidJvm]<br>suspend fun [startCountDown](start-count-down.md)()<br>Initiates a countdown process by decrementing the updated time in the current state at intervals of 1 second. |
| [startDiscovering](start-discovering.md) | [androidJvm]<br>fun [startDiscovering](start-discovering.md)()<br>Initiates the process of discovering study resources based on the selected time in the current state. |
| [startExploring](start-exploring.md) | [androidJvm]<br>fun [startExploring](start-exploring.md)()<br>Initiates the process of exploring a selected planet based on the selected time in the current state. |
| [stopDiscovering](stop-discovering.md) | [androidJvm]<br>fun [stopDiscovering](stop-discovering.md)()<br>Stops the process of discovering study resources based on the selected time in the current state. |
| [stopExploring](stop-exploring.md) | [androidJvm]<br>fun [stopExploring](stop-exploring.md)() |
