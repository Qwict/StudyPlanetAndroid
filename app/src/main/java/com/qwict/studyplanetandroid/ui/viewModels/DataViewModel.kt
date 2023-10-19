package com.qwict.studyplanetandroid.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.data.PlanetEntity
import com.qwict.studyplanetandroid.data.PlanetsRepository
import com.qwict.studyplanetandroid.data.StudyPlanetUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DataViewModel(
    val planetsRepository: PlanetsRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(
        StudyPlanetUiState(),
    )
    val uiState: StateFlow<StudyPlanetUiState> = _uiState.asStateFlow()

    init {
        _uiState = planetsRepository.getPlanets().map { StudyPlanetUiState(planets = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = StudyPlanetUiState(),
            ) as MutableStateFlow<StudyPlanetUiState>
    }
    fun getPlanets(): List<PlanetEntity> {
        return uiState.value.planets
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
