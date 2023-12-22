//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels](../index.md)/[UserViewModel](index.md)/[getLocalPlanets](get-local-planets.md)

# getLocalPlanets

[androidJvm]\
fun [getLocalPlanets](get-local-planets.md)()

Retrieves locally stored planet data and updates the [UserState](../../com.qwict.studyplanetandroid.presentation.viewmodels.states/-user-state/index.md) accordingly.

This function uses the getLocalPlanetsUseCase to retrieve locally stored planet data. It observes the result of the process and updates the [UserState](../../com.qwict.studyplanetandroid.presentation.viewmodels.states/-user-state/index.md) based on the result:

- 
   If the process is successful ([Resource.Success](../../com.qwict.studyplanetandroid.common/-resource/-success/index.md)), it updates the state with the retrieved planets.
- 
   If no local planets are available, it triggers the retrieval of online planets using [getOnlinePlanets](get-online-planets.md).
- 
   If an error occurs during the process ([Resource.Error](../../com.qwict.studyplanetandroid.common/-resource/-error/index.md)), it updates the state with the error message.
- 
   During the loading phase ([Resource.Loading](../../com.qwict.studyplanetandroid.common/-resource/-loading/index.md)), it updates the state to indicate loading.
