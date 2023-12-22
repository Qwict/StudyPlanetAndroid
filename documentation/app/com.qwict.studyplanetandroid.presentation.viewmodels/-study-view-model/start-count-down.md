//[app](../../../index.md)/[com.qwict.studyplanetandroid.presentation.viewmodels](../index.md)/[StudyViewModel](index.md)/[startCountDown](start-count-down.md)

# startCountDown

[androidJvm]\
suspend fun [startCountDown](start-count-down.md)()

Initiates a countdown process by decrementing the updated time in the current state at intervals of 1 second.

This function uses a while loop to decrement the [StudyState.updatedTime](../../com.qwict.studyplanetandroid.presentation.viewmodels.states/-study-state/updated-time.md) property by 1000 milliseconds (1 second) in each iteration. It calculates the remaining hours, minutes, and seconds based on the updated time and updates the corresponding properties in the state. The countdown continues until the updated time reaches zero.

The function introduces a delay of 1000 milliseconds between each iteration using delay to simulate the passage of time.

This function is a suspending function, allowing it to be called from a coroutine.
