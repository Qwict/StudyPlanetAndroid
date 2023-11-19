package com.qwict.studyplanetandroid.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.domain.use_case.planets.GetLocalPlanetsUseCase
import com.qwict.studyplanetandroid.domain.use_case.planets.GetOnlinePlanetsUseCase
import com.qwict.studyplanetandroid.domain.use_case.user.AuthenticateUseCase
import com.qwict.studyplanetandroid.presentation.viewmodels.states.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.math.ceil
import kotlin.math.log
import kotlin.math.pow

@HiltViewModel
class UserViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase,
    private val getLocalPlanetsUseCase: GetLocalPlanetsUseCase,
    private val getOnlinePlanetsUseCase: GetOnlinePlanetsUseCase,
) : ViewModel() {
    var state by mutableStateOf(UserState())
        private set

    init {
        getUserWithToken()
    }

    fun getLocalPlanets() {
        Log.i("UserViewModel", "getPlanets: ")
        getLocalPlanetsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state = state.copy(planets = result.data ?: emptyList())
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

    fun getOnlinePlanets() {
        Log.i("UserViewModel", "getPlanets: ")
        getOnlinePlanetsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state = state.copy(planets = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    state = state.copy(refreshError = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    state = state.copy(isRefreshing = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed class ValidationEvent {
        data object Success : ValidationEvent()
    }

    private fun getUserWithToken() {
        authenticateUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state = UserState(user = result.data!!)
                    levelCalculator(result.data.experience)
                }

                is Resource.Error -> {
                    state = state.copy(error = result.message ?: "An unexpected error occurred", isLoading = false)
                }

                is Resource.Loading -> {
                    state = state.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun levelCalculator(experience: Int) {
        Log.d("AuthViewModel", "levelCalculator: ${log(experience.toDouble() / 60, 2.0)}")

        val currentLevel = ceil(log(experience.toDouble() / 60, 2.0))
        val experienceForCurrentLevel = (2.0.pow(currentLevel - 1) * 60)
        val experienceForNextLevel = (2.0.pow(currentLevel) * 60) - experience + experienceForCurrentLevel
        val experienceProgress = (experience - experienceForCurrentLevel) / (experienceForNextLevel)

        if (experience == 0) {
            state = state.copy(currentLevel = 0, experienceForNextLevel = 2)
        } else {
            state = state.copy(
                currentLevel = currentLevel.toInt(),
                experienceForNextLevel = experienceForNextLevel.toInt(),
                experienceProgress = experienceProgress.toFloat(),
            )
        }
        Log.d("AuthViewModel", "levelCalculator: ${state.currentLevel}, ${state.experienceForNextLevel}, ${state.experienceProgress}")
    }
}
