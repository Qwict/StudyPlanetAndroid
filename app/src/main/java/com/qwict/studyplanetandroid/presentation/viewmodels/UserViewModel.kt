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

    private var _state by mutableStateOf(AuthState())
    val state: AuthState = _state

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvent = validationEventChannel.receiveAsFlow()

    init {
        getUserWithToken()
    }

    private fun getUserWithToken() {
        authenticateUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state = AuthState(user = result.data!!)
                    levelCalculator(result.data.experience)
                }

                is Resource.Error -> {
                    _state = _state.copy(error = result.message ?: "An unexpected error occurred", isLoading = false)
                }

                is Resource.Loading -> {
                    _state = _state.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loginUser() {
        Log.i("AuthViewModel", "loginUser: $state.email, $state.password")
        loginUseCase(state.email, state.password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state = AuthState(user = result.data!!)
                    levelCalculator(_state.user.experience)
                }

                is Resource.Error -> {
                    _state =
                        AuthState(error = result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                    _state = AuthState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun switchIsRegistering() {
        _state = _state.copy(registerNewUser = !_state.registerNewUser)
    }

    fun onEvent(event: AuthenticationFormEvent) {
        when (event) {
            is AuthenticationFormEvent.UsernameChanged -> {
                _state = _state.copy(username = event.username)
            }
            is AuthenticationFormEvent.EmailChanged -> {
                _state = _state.copy(email = event.email)
            }
            is AuthenticationFormEvent.PasswordChanged -> {
                _state = _state.copy(password = event.password)
            }
            is AuthenticationFormEvent.ConfirmPasswordChanged -> {
                _state = _state.copy(confirmPassword = event.confirmPassword)
            }
            is AuthenticationFormEvent.RegisterClicked -> {
                registerUser()
            }
        }
    }

    fun registerUser() {
        val emailResult = validators.emailValidator(state.email)
        var passwordResult = ValidationResult(successful = true)

        // Only validate password if a user is registering, could change passwordResult to a error message
        if (!emailResult.successful) {
            passwordResult = validators.passwordValidator(state.password, state.confirmPassword)
        }

        val hasErrors = listOf(
            emailResult,
            passwordResult,
        ).any { !it.successful }

        if (hasErrors) {
            _state = _state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                confirmPasswordError = passwordResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            // will notify the ui that the validation was successful (ui is an observer here of the channel)
            validationEventChannel.send(ValidationEvent.Success)
        }

        registerUseCase(state.username, state.email, state.password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state = AuthState(user = result.data!!)
                    levelCalculator(state.user.experience)
                }
                is Resource.Error -> {
                    _state = AuthState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state = AuthState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
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
            _state = _state.copy(currentLevel = 0, experienceForNextLevel = 2)
        } else {
            _state = _state.copy(
                currentLevel = currentLevel.toInt(),
                experienceForNextLevel = experienceForNextLevel.toInt(),
                experienceProgress = experienceProgress.toFloat(),
            )
        }
        Log.d("AuthViewModel", "levelCalculator: ${_state.currentLevel}, ${_state.experienceForNextLevel}, ${_state.experienceProgress}")
    }
}
