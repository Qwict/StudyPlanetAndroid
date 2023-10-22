package com.qwict.studyplanetandroid.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.data.PlanetEntity
import com.qwict.studyplanetandroid.data.PlanetsRepository
import com.qwict.studyplanetandroid.data.StudyPlanetUiState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataViewModel(
    val planetsRepository: PlanetsRepository,
) : ViewModel() {

    private var _uiState by mutableStateOf(StudyPlanetUiState())

    init {
        viewModelScope.launch {
            _uiState = planetsRepository.getPlanets().map { StudyPlanetUiState(planets = it) }
                .filterNotNull().first()
        }
    }
    fun getPlanets(): List<PlanetEntity> {
        return _uiState.planets
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
