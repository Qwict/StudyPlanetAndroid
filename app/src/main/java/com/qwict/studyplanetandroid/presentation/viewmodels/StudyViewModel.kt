package com.qwict.studyplanetandroid.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.qwict.studyplanetandroid.common.Constants.EMPTY_PLANET
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.presentation.viewmodels.states.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class StudyViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    var selectedPlanet: Planet by mutableStateOf(EMPTY_PLANET)
    var isDiscovering = mutableStateOf(false)
    var selectedTime by mutableStateOf(0)
    var hours by mutableStateOf(0)
    var minutes by mutableStateOf(0)
    var seconds by mutableStateOf(0)
    var updatedTime by mutableStateOf(0)

    fun startExploring(selectedTime: Long) {
    }

    fun stopExploring() {
    }
    fun startDiscovering(selectedTime: Int) {
    }

    fun stopDiscovering() {
    }
    fun resetAction() {
        isDiscovering.value = false
        selectedTime = 0
        hours = 0
        minutes = 0
        seconds = 0
        updatedTime = 0
    }
    suspend fun countDown() {
        while (updatedTime > 0) {
            updatedTime -= 1000
            hours = (updatedTime / (1000 * 60 * 60)).toInt()
            minutes = (updatedTime % (1000 * 60 * 60) / (1000 * 60)).toInt()
            seconds = (updatedTime % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
            delay(1000)
        }
    }
}
