package com.qwict.studyplanetandroid.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.data.StudyPlanetRepository
import com.qwict.studyplanetandroid.presentation.viewmodels.states.DiscoveredPlanetsScreenState
import com.qwict.studyplanetandroid.presentation.viewmodels.states.DiscoveredPlanetsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: StudyPlanetRepository,
) : ViewModel() {
    var state: DiscoveredPlanetsState by mutableStateOf(DiscoveredPlanetsState.Loading)
        private set
    var screenState: DiscoveredPlanetsScreenState by mutableStateOf(DiscoveredPlanetsScreenState())
        private set
    fun getDiscoveredPlanets() {
        viewModelScope.launch {
            state = DiscoveredPlanetsState.Loading
            state = try {
                val planets = repository.getDiscoveredPlanets().stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = emptyList(),
                )
                DiscoveredPlanetsState.Success(planets)
            } catch (e: Exception) {
                DiscoveredPlanetsState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }

    fun getDiscoveredPlanetsOnline() {
        viewModelScope.launch {
            Log.d("DiscoverViewModel", "getDiscoveredPlanetsOnline")
            repository.refreshDiscoveredPlanetsOnline().onEach { result ->
                screenState = when (result) {
                    is Resource.Success -> {
                        DiscoveredPlanetsScreenState()
                    }

                    is Resource.Error -> {
                        DiscoveredPlanetsScreenState(
                            refreshError = result.message ?: "An unexpected error occurred",
                        )
                    }

                    is Resource.Loading -> {
                        DiscoveredPlanetsScreenState(isRefreshing = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}
