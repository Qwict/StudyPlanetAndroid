package com.qwict.studyplanetandroid.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.domain.use_case.user.AuthenticateUseCase
import com.qwict.studyplanetandroid.domain.use_case.user.LoginUseCase
import com.qwict.studyplanetandroid.domain.use_case.user.RegisterUseCase
import com.qwict.studyplanetandroid.domain.use_case.validator.ValidationResult
import com.qwict.studyplanetandroid.domain.use_case.validator.Validators
import com.qwict.studyplanetandroid.presentation.viewmodels.sealed.AuthenticationFormEvent
import com.qwict.studyplanetandroid.presentation.viewmodels.states.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log
import kotlin.math.pow

@HiltViewModel
class UserViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val authenticateUseCase: AuthenticateUseCase,
    private val validators: Validators,
) : ViewModel() {
    var state by mutableStateOf(AuthState())
        private set

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvent = validationEventChannel.receiveAsFlow()

    init {
        getUserWithToken()
    }

    private fun getUserWithToken() {
        authenticateUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state = AuthState(user = result.data!!)
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

    fun loginUser() {
        Log.i("AuthViewModel", "loginUser: $state.email, $state.password")
        loginUseCase(state.email, state.password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state = AuthState(user = result.data!!)
                    levelCalculator(state.user.experience)
                }

                is Resource.Error -> {
                    state =
                        AuthState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    state = AuthState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun switchIsRegistering() {
        state = state.copy(
            registerNewUser = !state.registerNewUser,
            error = "",
        )
    }

    fun onEvent(event: AuthenticationFormEvent) {
        when (event) {
            is AuthenticationFormEvent.UsernameChanged -> {
                state = state.copy(username = event.username)
            }
            is AuthenticationFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is AuthenticationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is AuthenticationFormEvent.ConfirmPasswordChanged -> {
                state = state.copy(confirmPassword = event.confirmPassword)
            }
            is AuthenticationFormEvent.RegisterClicked -> {
                registerUser()
            }
        }
    }

    fun registerUser() {
        val emailResult = validators.emailValidator(state.email)
        var passwordResult = ValidationResult(successful = true)
        var usernameResult = ValidationResult(successful = true)

        // Only validate password if a user is registering, could change passwordResult to a error message
        if (state.registerNewUser) {
            usernameResult = validators.usernameValidator(state.username)
            passwordResult = validators.passwordValidator(state.password, state.confirmPassword)
        }

        val hasErrors = listOf(
            emailResult,
            usernameResult,
            passwordResult,
        ).any { !it.successful }

        if (hasErrors) {
            state = state.copy(
                usernameError = usernameResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                confirmPasswordError = passwordResult.errorMessage,
            )
            return
        } else {
            registerUseCase(state.username, state.email, state.password).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state = AuthState(user = result.data!!)
                        levelCalculator(state.user.experience)
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false,
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            error = "",
                            isLoading = true,
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }

        viewModelScope.launch {
            // will notify the ui that the validation was successful (ui is an observer here of the channel)
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        data object Success : ValidationEvent()
    }

    private fun levelCalculator(experience: Int) {
        Log.d("AuthViewModel", "levelCalculator: ${log(experience.toDouble() / 60, 2.0)}")

        val currentLevel = Math.ceil(log(experience.toDouble() / 60, 2.0))
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
