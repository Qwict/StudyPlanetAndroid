package com.qwict.studyplanetandroid.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.domain.use_case.actions.StartDiscoveringUseCase
import com.qwict.studyplanetandroid.domain.use_case.actions.StartExploringUseCase
import com.qwict.studyplanetandroid.domain.use_case.actions.StopDiscoveringUseCase
import com.qwict.studyplanetandroid.domain.use_case.actions.StopExploringUseCase
import com.qwict.studyplanetandroid.presentation.viewmodels.states.StudyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StudyViewModel @Inject constructor(
    private val startDiscoveringUseCase: StartDiscoveringUseCase,
    private val stopDiscoveringUseCase: StopDiscoveringUseCase,
    private val startExploringUseCase: StartExploringUseCase,
    private val stopExploringUseCase: StopExploringUseCase,
) : ViewModel() {

    var state by mutableStateOf(StudyState())
        private set

    /**
     * Initiates the process of discovering study resources based on the selected time in the current state.
     *
     * This function calls the [startDiscoveringUseCase] to begin the process of discovering study resources
     * based on the selected time in the current state. It observes the result of the process and updates the state
     * accordingly based on the result:
     * - If the process is successful ([Resource.Success]), it logs the result and updates the state to its initial state.
     * - If an error occurs during the process ([Resource.Error]), it updates the state with the error message.
     * - During the loading phase ([Resource.Loading]), it updates the state to indicate loading.
     *
     * @throws IllegalStateException if the [startDiscoveringUseCase] is not provided or initialized.
     */
    fun startDiscovering() {
        Log.i("StudyViewModel", "startDiscovering")
        startDiscoveringUseCase(state.selectedTime).onEach { result ->
            state = when (result) {
                is Resource.Success -> {
                    Log.i("StudyViewModel", "startDiscovering: ${result.data}")
                    StudyState()
                }

                is Resource.Error -> {
                    state.copy(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    state.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Stops the process of discovering study resources based on the selected time in the current state.
     *
     * This function calls the [stopDiscoveringUseCase] to halt the process of discovering study resources
     * based on the selected time in the current state. It observes the result of the process and updates the state
     * accordingly based on the result:
     * - If the process is successful ([Resource.Success]), it logs the result and updates the state with the discovered planet information,
     *   setting flags indicating the discovery status and the intention to open the planet discovered dialog.
     * - If no planet is discovered (result.data is null), it updates the state with flags indicating no discovery
     *   and the intention to open the planet discovered dialog.
     * - If an error occurs during the process ([Resource.Error]), it updates the state with the error message.
     * - During the loading phase ([Resource.Loading]), it updates the state to indicate loading.
     *
     * @throws IllegalStateException if the [stopDiscoveringUseCase] is not provided or initialized.
     */
    fun stopDiscovering() {
        Log.i("StudyViewModel", "stopDiscovering")
        stopDiscoveringUseCase(state.selectedTime).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.i("StudyViewModel", "stopDiscovering: ${result.data}")
                    if (result.data != null) {
                        state = state.copy(
                            discoveredPlanet = result.data,
                            hasDiscoveredPlanet = true,
                            openPlanetDiscoveredDialog = true,
                        )
                    } else {
                        state = state.copy(
                            hasDiscoveredPlanet = false,
                            openPlanetDiscoveredDialog = true,
                        )
                    }
                }

                is Resource.Error -> {
                    state = state.copy(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Initiates the process of exploring a selected planet based on the selected time in the current state.
     *
     * This function calls the [startExploringUseCase] to begin the process of exploring a selected planet
     * based on the selected time and the ID of the selected planet in the current state. It observes the result
     * of the process and updates the state accordingly based on the result:
     * - If the process is successful ([Resource.Success]), it logs the result and updates the state to its initial state.
     * - If an error occurs during the process ([Resource.Error]), it updates the state with the error message.
     * - During the loading phase ([Resource.Loading]), it updates the state to indicate loading.
     *
     * @throws IllegalStateException if the [startExploringUseCase] is not provided or initialized.
     */
    fun startExploring() {
        Log.i("StudyViewModel", "startExploring")
        startExploringUseCase(state.selectedTime, selectedPlanetId = state.selectedPlanet.id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.i("StudyViewModel", "startExploring: ${result.data}")
                    state = StudyState()
                }

                is Resource.Error -> {
                    state = state.copy(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun stopExploring() {
    }

    fun closeBackAlertDialog() { state = state.copy(openOnBackAlertDialog = false) }
    fun openOnBackAlertDialog() { state = state.copy(openOnBackAlertDialog = true) }

    /**
     * Resets the study state by setting relevant properties to their initial values.
     *
     * This function resets the study state by setting the following properties to their initial values:
     * - [StudyState.selectedTime]: 0 (zero)
     * - [StudyState.hours]: 0 (zero)
     * - [StudyState.minutes]: 0 (zero)
     * - [StudyState.seconds]: 0 (zero)
     * - [StudyState.updatedTime]: 0 (zero)
     *
     * It effectively initializes the state for a new study session.
     */
    fun resetAction() {
        state.selectedTime = 0
        state.hours = 0
        state.minutes = 0
        state.seconds = 0
        state.updatedTime = 0
    }

    /**
     * Initiates a countdown process by decrementing the updated time in the current state at intervals of 1 second.
     *
     * This function uses a [while] loop to decrement the [StudyState.updatedTime] property by 1000 milliseconds (1 second)
     * in each iteration. It calculates the remaining hours, minutes, and seconds based on the updated time and updates
     * the corresponding properties in the state. The countdown continues until the updated time reaches zero.
     *
     * The function introduces a delay of 1000 milliseconds between each iteration using [delay] to simulate the passage
     * of time.
     *
     * This function is a suspending function, allowing it to be called from a coroutine.
     */
    suspend fun startCountDown() {
        while (state.updatedTime > 0) {
            state.updatedTime -= 1000
            state.hours = (state.updatedTime / (1000 * 60 * 60)).toInt()
            state.minutes = (state.updatedTime % (1000 * 60 * 60) / (1000 * 60)).toInt()
            state.seconds = (state.updatedTime % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
            delay(1000)
        }
    }
}
