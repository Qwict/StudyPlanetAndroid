package com.qwict.studyplanetandroid.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.domain.use_case.get_user.AuthenticateUseCase
import com.qwict.studyplanetandroid.domain.use_case.get_user.LoginUseCase
import com.qwict.studyplanetandroid.presentation.viewmodels.states.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.math.log
import kotlin.math.pow

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val authenticateUseCase: AuthenticateUseCase,
) : ViewModel() {

    private var _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    init {
        getUserWithToken()
    }

    private fun getUserWithToken() {
        authenticateUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AuthState(user = result.data!!)
                    levelCalculator(_state.value.user.experience)
                }

                is Resource.Error -> {
                    _state.value =
                        AuthState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = AuthState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loginUser(email: String, password: String) {
        Log.i("AuthViewModel", "loginUser: $email, $password")
        loginUseCase(email, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AuthState(user = result.data!!)
                }

                is Resource.Error -> {
                    _state.value =
                        AuthState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state.value = AuthState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun levelCalculator(experience: Int) {
        Log.d("AuthViewModel", "levelCalculator: ${log(experience.toDouble() / 60, 2.0)}")

        val currentLevel = Math.ceil(log(experience.toDouble() / 60, 2.0))
        val experienceForCurrentLevel = (2.0.pow(currentLevel - 1) * 60)
        val experienceForNextLevel = (2.0.pow(currentLevel) * 60) - experience + experienceForCurrentLevel
        val experienceProgress = (experience - experienceForCurrentLevel) / (experienceForNextLevel)

        if (experience == 0) {
            _state.value = _state.value.copy(currentLevel = 0, experienceForNextLevel = 2)
        } else {
            _state.value = _state.value.copy(
                currentLevel = currentLevel.toInt(),
                experienceForNextLevel = experienceForNextLevel.toInt(),
                experienceProgress = experienceProgress.toFloat(),
            )
        }
        Log.d("AuthViewModel", "levelCalculator: ${_state.value.currentLevel}, ${_state.value.experienceForNextLevel}, ${_state.value.experienceProgress}")
    }
}
