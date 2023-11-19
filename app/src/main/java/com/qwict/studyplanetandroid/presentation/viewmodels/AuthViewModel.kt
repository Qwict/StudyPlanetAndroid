package com.qwict.studyplanetandroid.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.common.Resource
import com.qwict.studyplanetandroid.domain.use_case.user.LoginUseCase
import com.qwict.studyplanetandroid.domain.use_case.user.RegisterUseCase
import com.qwict.studyplanetandroid.domain.validator.Validators
import com.qwict.studyplanetandroid.presentation.viewmodels.sealed.AuthenticationFormEvent
import com.qwict.studyplanetandroid.presentation.viewmodels.states.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val validators: Validators,
) : ViewModel() {
    var state by mutableStateOf(AuthState())
        private set

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvent = validationEventChannel.receiveAsFlow()

    sealed class ValidationEvent {
        data object Success : ValidationEvent()
    }

    fun switchPasswordVisibility() {
        state = state.copy(isPasswordVisible = !state.isPasswordVisible)
    }

    fun loginUser() {
        val emailResult = validators.emailValidator(state.email)
        val passwordResult = validators.notEmptyTextValidator(state.password, "Password")

        val hasErrors = listOf(
            passwordResult,
            emailResult,
        ).any { !it.successful }

        if (hasErrors) {
            state = state.copy(
                passwordError = passwordResult.errorMessage,
                emailError = emailResult.errorMessage,
            )
            return
        } else {
            loginUseCase(state.email, state.password).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state = AuthState(user = result.data!!)
//                    levelCalculator(state.user.experience)
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false,
                            emailError = "",
                            passwordError = "",
                        )
                    }

                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
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

    fun clearValidationErrors() {
        state = state.copy(
            usernameError = "",
            emailError = "",
            passwordError = "",
            confirmPasswordError = "",
            error = "",
        )
    }
    fun registerUser() {
        val emailResult = validators.emailValidator(state.email)
        val usernameResult = validators.usernameValidator(state.username)
        val passwordResult = validators.passwordValidator(state.password, state.confirmPassword)

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
//                        levelCalculator(state.user.experience)
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
}
