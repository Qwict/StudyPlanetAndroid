package com.qwict.studyplanetandroid.presentation.user

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.domain.use_case.get_planets.GetLocalPlanetsUseCase
import com.qwict.studyplanetandroid.domain.use_case.get_planets.GetOnlinePlanetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getLocalPlanetsUseCase: GetLocalPlanetsUseCase,
    private val getOnlinePlanetsUseCase: GetOnlinePlanetsUseCase,
) : ViewModel() {
    private var _state = mutableStateOf(UserState())
    val state: State<UserState> = _state

    fun getLocalPlanets() {
        Log.i("UserViewModel", "getPlanets: ")
        getLocalPlanetsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UserState(planets = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value =
                        UserState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = UserState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getOnlinePlanets() {
        Log.i("UserViewModel", "getPlanets: ")
        getOnlinePlanetsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UserState(planets = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(refreshError = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isRefreshing = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
