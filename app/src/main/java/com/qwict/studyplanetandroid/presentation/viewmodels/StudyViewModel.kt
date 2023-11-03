package com.qwict.studyplanetandroid.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private var _state = mutableStateOf(StudyState())
    val state: State<StudyState> = _state

    fun startDiscovering() {
        Log.i("StudyViewModel", "startDiscovering")
        startDiscoveringUseCase(_state.value.selectedTime).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.i("StudyViewModel", "startDiscovering: ${result.data}")
                    _state.value = StudyState()
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun stopDiscovering() {
        Log.i("StudyViewModel", "stopDiscovering")
        stopDiscoveringUseCase(_state.value.selectedTime).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.i("StudyViewModel", "stopDiscovering: ${result.data}")
                    if (result.data != null) {
                        _state.value = _state.value.copy(
                            discoveredPlanet = result.data,
                            haseDiscoveredPlanet = true,
                        )
                    }
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun startExploring() {
        Log.i("StudyViewModel", "startExploring")
        startExploringUseCase(_state.value.selectedTime, selectedPlanetId = state.value.selectedPlanet.id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.i("StudyViewModel", "startExploring: ${result.data}")
                    _state.value = StudyState()
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun stopExploring() {
    }
    fun resetAction() {
        _state.value.selectedTime = 0
        _state.value.hours = 0
        _state.value.minutes = 0
        _state.value.seconds = 0
        _state.value.updatedTime = 0
    }
    suspend fun startCountDown() {
        while (_state.value.updatedTime > 0) {
            _state.value.updatedTime -= 1000
            _state.value.hours = (_state.value.updatedTime / (1000 * 60 * 60)).toInt()
            _state.value.minutes = (_state.value.updatedTime % (1000 * 60 * 60) / (1000 * 60)).toInt()
            _state.value.seconds = (_state.value.updatedTime % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
            delay(1000)
        }
    }
}
