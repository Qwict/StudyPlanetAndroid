//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels](../index.md)/[StudyViewModel](index.md)/[startDiscovering](start-discovering.md)

# startDiscovering

[androidJvm]\
fun [startDiscovering](start-discovering.md)()

Initiates the process of discovering study resources based on the selected time in the current state.

This function calls the startDiscoveringUseCase to begin the process of discovering study resources based on the selected time in the current state. It observes the result of the process and updates the state accordingly based on the result:

- 
   If the process is successful ([Resource.Success](../../com.qwict.studyplanetandroid.common/-resource/-success/index.md)), it logs the result and updates the state to its initial state.
- 
   If an error occurs during the process ([Resource.Error](../../com.qwict.studyplanetandroid.common/-resource/-error/index.md)), it updates the state with the error message.
- 
   During the loading phase ([Resource.Loading](../../com.qwict.studyplanetandroid.common/-resource/-loading/index.md)), it updates the state to indicate loading.

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) | if the startDiscoveringUseCase is not provided or initialized. |
