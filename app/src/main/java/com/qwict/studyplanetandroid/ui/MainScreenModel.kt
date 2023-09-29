package com.qwict.studyplanetandroid.ui

import androidx.lifecycle.ViewModel
import com.qwict.studyplanetandroid.data.StudyPlanetUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenModel : ViewModel() {

    private val _uiState = MutableStateFlow(StudyPlanetUiState())
    val uiState: StateFlow<StudyPlanetUiState> = _uiState.asStateFlow()

    fun setSelectedPlanet(planet: String) {
        _uiState.update { currentState ->
            currentState.copy(selectedPlanet = planet)
        }
    }
}
