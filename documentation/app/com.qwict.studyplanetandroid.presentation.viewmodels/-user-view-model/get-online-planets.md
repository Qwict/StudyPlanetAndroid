//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels](../index.md)/[UserViewModel](index.md)/[getOnlinePlanets](get-online-planets.md)

# getOnlinePlanets

[androidJvm]\
fun [getOnlinePlanets](get-online-planets.md)(showRefreshing: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))

Retrieves planet data from an online source and updates the [UserState](../../com.qwict.studyplanetandroid.presentation.viewmodels.states/-user-state/index.md) accordingly.

This function uses the getOnlinePlanetsUseCase to retrieve planet data from an online source. It observes the result of the process and updates the [UserState](../../com.qwict.studyplanetandroid.presentation.viewmodels.states/-user-state/index.md) based on the result:

- 
   If the process is successful ([Resource.Success](../../com.qwict.studyplanetandroid.common/-resource/-success/index.md)), it updates the state with the retrieved online planets.
- 
   If an error occurs during the process ([Resource.Error](../../com.qwict.studyplanetandroid.common/-resource/-error/index.md)), it updates the state with the error message.
- 
   During the loading phase ([Resource.Loading](../../com.qwict.studyplanetandroid.common/-resource/-loading/index.md)), it updates the state to indicate loading.

#### Parameters

androidJvm

| | |
|---|---|
| showRefreshing | Flag indicating whether to show a refreshing indicator during the process. |
