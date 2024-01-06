package com.qwict.studyplanetandroid.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.domain.actions.StartDiscoveringUseCase
import com.qwict.studyplanetandroid.domain.actions.StartExploringUseCase
import com.qwict.studyplanetandroid.domain.actions.StopDiscoveringUseCase
import com.qwict.studyplanetandroid.domain.actions.StopExploringUseCase
import com.qwict.studyplanetandroid.presentation.viewmodels.states.StudyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudyViewModel
    @Inject
    constructor(
        private val startDiscoveringUseCase: StartDiscoveringUseCase,
        private val stopDiscoveringUseCase: StopDiscoveringUseCase,
        private val startExploringUseCase: StartExploringUseCase,
        private val stopExploringUseCase: StopExploringUseCase,
    ) : ViewModel() {
        var state by mutableStateOf(StudyState())
            private set

        suspend fun startStudyTimer(
            selectedTimeInMinutes: Float,
            selectedPlanetId: Int,
        ) {
            viewModelScope.launch {
                if (state.isRunning) {
                    return@launch
                } else {
                    state = state.copy(isRunning = true)
                }
                Log.d(
                    "StudyViewModel",
                    "Started ${if (selectedPlanetId == 0) "discovering" else "exploring"} for $selectedTimeInMinutes minutes",
                )
                try {
                    val selectedTimeInMillis = selectedTimeInMinutes.toInt() * 60 * 1000
                    if (selectedPlanetId == 0) {
                        startDiscovering(selectedTimeInMillis)
                    } else {
                        startExploring(selectedTimeInMillis, selectedPlanetId)
                    }

                    val startTimeInMillis = System.currentTimeMillis()
                    val endTimeInMillis = startTimeInMillis + selectedTimeInMillis
                    while (endTimeInMillis > System.currentTimeMillis()) {
                        Log.d("StudyViewModel", "${System.currentTimeMillis()}")
                        val hours = (endTimeInMillis - System.currentTimeMillis()) / 1000 / 60 / 60
                        val minutes = (endTimeInMillis - System.currentTimeMillis()) / 1000 / 60 % 60
                        val seconds = (endTimeInMillis - System.currentTimeMillis()) / 1000 % 60
                        val currentProgress = ((System.currentTimeMillis() - startTimeInMillis) / selectedTimeInMillis.toDouble())
                        state =
                            state.copy(
                                hours = hours.toInt(),
                                minutes = minutes.toInt(),
                                seconds = seconds.toInt(),
                                currentProgress = currentProgress.toFloat(),
                                progressPercentage = (currentProgress * 100).toInt(),
                            )
                        delay(500)
                    }
                    if (selectedPlanetId == 0) {
                        stopDiscovering()
                    } else {
                        stopExploring(selectedTimeInMillis, selectedPlanetId)
                    }
                } catch (e: Exception) {
                    Log.i("ExplorerScreen", e.toString())
                }
            }
        }

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
        private fun startDiscovering(selectedTimeInMillis: Int) {
            Log.i("StudyViewModel", "startDiscovering")
            startDiscoveringUseCase(selectedTimeInMillis).onEach { result ->
                state =
                    when (result) {
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
        private fun stopDiscovering() {
            Log.i("StudyViewModel", "stopDiscovering")
            stopDiscoveringUseCase(state.selectedTime).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        Log.i("StudyViewModel", "stopDiscovering: ${result.data}")
                        state =
                            state.copy(
                                discoveredPlanet = result.data?.planet ?: state.discoveredPlanet,
                                hasDiscoveredPlanet = result.data?.hasFoundNewPlanet ?: false,
                                openPlanetDiscoveredDialog = true,
                                gainedExperience = result.data?.gainedExperience ?: 0,
                            )
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
        private fun startExploring(
            selectedTimeInMillis: Int,
            selectedPlanetId: Int,
        ) {
            Log.i("StudyViewModel", "startExploring")
            startExploringUseCase(selectedTimeInMillis = selectedTimeInMillis, selectedPlanetId = selectedPlanetId).onEach { result ->
                state =
                    when (result) {
                        is Resource.Success -> {
                            Log.i("StudyViewModel", "startExploring: ${result.data}")
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

        private fun stopExploring(
            selectedTimeInMillis: Int,
            selectedPlanetId: Int,
        ) {
            stopExploringUseCase(selectedTimeInMillis = selectedTimeInMillis, selectedPlanetId = selectedPlanetId).onEach { result ->
                state =
                    when (result) {
                        is Resource.Success -> {
                            Log.i("StudyViewModel", "startExploring: ${result.data}")
                            state.copy(
                                gainedExperience = result.data!!,
                                openPlanetDiscoveredDialog = true,
                            )
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

        fun closeBackAlertDialog() {
            state = state.copy(openOnBackAlertDialog = false)
        }

        fun openOnBackAlertDialog() {
            state = state.copy(openOnBackAlertDialog = true)
        }
    }
