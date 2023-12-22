//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels](../index.md)/[StudyViewModel](index.md)/[startExploring](start-exploring.md)

# startExploring

[androidJvm]\
fun [startExploring](start-exploring.md)()

Initiates the process of exploring a selected planet based on the selected time in the current state.

This function calls the startExploringUseCase to begin the process of exploring a selected planet based on the selected time and the ID of the selected planet in the current state. It observes the result of the process and updates the state accordingly based on the result:

- 
   If the process is successful ([Resource.Success](../../com.qwict.studyplanetandroid.common/-resource/-success/index.md)), it logs the result and updates the state to its initial state.
- 
   If an error occurs during the process ([Resource.Error](../../com.qwict.studyplanetandroid.common/-resource/-error/index.md)), it updates the state with the error message.
- 
   During the loading phase ([Resource.Loading](../../com.qwict.studyplanetandroid.common/-resource/-loading/index.md)), it updates the state to indicate loading.

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) | if the startExploringUseCase is not provided or initialized. |
