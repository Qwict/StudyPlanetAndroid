package com.qwict.studyplanetandroid.presentation.viewmodels.states

import com.qwict.studyplanetandroid.domain.model.Planet
import kotlinx.coroutines.flow.StateFlow

sealed class DiscoveredPlanetsState {
    data class Success(val discoveredPlanets: StateFlow<List<Planet>>) : DiscoveredPlanetsState()
    data class Error(val exception: String) : DiscoveredPlanetsState()
    data object Loading : DiscoveredPlanetsState()
}

data class DiscoveredPlanetsScreenState(
    val isRefreshing: Boolean = false,
    val refreshError: String = "",
)
