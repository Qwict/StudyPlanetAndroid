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

    fun startDiscovering() {
        Log.i("StudyViewModel", "startDiscovering")
        startDiscoveringUseCase(state.selectedTime).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.i("StudyViewModel", "startDiscovering: ${result.data}")
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

    fun resetAction() {
        state.selectedTime = 0
        state.hours = 0
        state.minutes = 0
        state.seconds = 0
        state.updatedTime = 0
    }
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
