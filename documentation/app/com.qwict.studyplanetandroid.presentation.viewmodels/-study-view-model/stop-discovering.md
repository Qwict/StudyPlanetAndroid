//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels](../index.md)/[StudyViewModel](index.md)/[stopDiscovering](stop-discovering.md)

# stopDiscovering

[androidJvm]\
fun [stopDiscovering](stop-discovering.md)()

Stops the process of discovering study resources based on the selected time in the current state.

This function calls the stopDiscoveringUseCase to halt the process of discovering study resources based on the selected time in the current state. It observes the result of the process and updates the state accordingly based on the result:

- 
   If the process is successful ([Resource.Success](../../com.qwict.studyplanetandroid.common/-resource/-success/index.md)), it logs the result and updates the state with the discovered planet information, setting flags indicating the discovery status and the intention to open the planet discovered dialog.
- 
   If no planet is discovered (result.data is null), it updates the state with flags indicating no discovery and the intention to open the planet discovered dialog.
- 
   If an error occurs during the process ([Resource.Error](../../com.qwict.studyplanetandroid.common/-resource/-error/index.md)), it updates the state with the error message.
- 
   During the loading phase ([Resource.Loading](../../com.qwict.studyplanetandroid.common/-resource/-loading/index.md)), it updates the state to indicate loading.

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) | if the stopDiscoveringUseCase is not provided or initialized. |
