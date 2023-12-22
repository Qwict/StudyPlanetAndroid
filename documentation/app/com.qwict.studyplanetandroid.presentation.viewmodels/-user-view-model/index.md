//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels](../index.md)/[UserViewModel](index.md)

# UserViewModel

[androidJvm]\
class [UserViewModel](index.md)@Injectconstructor(authenticateUseCase: [AuthenticateUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-authenticate-use-case/index.md), getLocalPlanetsUseCase: [GetLocalPlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-local-planets-use-case/index.md), getOnlinePlanetsUseCase: [GetOnlinePlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-online-planets-use-case/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

ViewModel responsible for managing user-related functionality and state.

The [UserViewModel](index.md) class is a Hilt-aware ViewModel that orchestrates interactions between the UI and the underlying use cases for user authentication and planet data retrieval. It also handles the calculation of user levels based on experience points.

## Constructors

| | |
|---|---|
| [UserViewModel](-user-view-model.md) | [androidJvm]<br>@Inject<br>constructor(authenticateUseCase: [AuthenticateUseCase](../../com.qwict.studyplanetandroid.domain.use_case.user/-authenticate-use-case/index.md), getLocalPlanetsUseCase: [GetLocalPlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-local-planets-use-case/index.md), getOnlinePlanetsUseCase: [GetOnlinePlanetsUseCase](../../com.qwict.studyplanetandroid.domain.use_case.planets/-get-online-planets-use-case/index.md)) |

## Types

| Name | Summary |
|---|---|
| [ValidationEvent](-validation-event/index.md) | [androidJvm]<br>sealed class [ValidationEvent](-validation-event/index.md) |

## Properties

| Name | Summary |
|---|---|
| [state](state.md) | [androidJvm]<br>var [state](state.md): [UserState](../../com.qwict.studyplanetandroid.presentation.viewmodels.states/-user-state/index.md) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [getLocalPlanets](get-local-planets.md) | [androidJvm]<br>fun [getLocalPlanets](get-local-planets.md)()<br>Retrieves locally stored planet data and updates the [UserState](../../com.qwict.studyplanetandroid.presentation.viewmodels.states/-user-state/index.md) accordingly. |
| [getOnlinePlanets](get-online-planets.md) | [androidJvm]<br>fun [getOnlinePlanets](get-online-planets.md)(showRefreshing: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Retrieves planet data from an online source and updates the [UserState](../../com.qwict.studyplanetandroid.presentation.viewmodels.states/-user-state/index.md) accordingly. |
