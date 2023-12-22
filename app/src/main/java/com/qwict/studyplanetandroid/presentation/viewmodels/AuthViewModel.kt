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

/**
 * ViewModel responsible for handling authentication-related functionality in the application.
 *
 * The [AuthViewModel] class is a Hilt-aware ViewModel that manages authentication-related use cases,
 * such as user login and registration, along with validators for input validation.
 *
 * @property loginUseCase The use case responsible for user login.
 * @property registerUseCase The use case responsible for user registration.
 * @property validators Validators for input validation in the authentication process.
 */
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

    /**
     * Initiates the user login process based on the provided email and password in the current state.
     *
     * This function performs validation on the provided email and password using the specified validators.
     * If validation fails, it updates the state with corresponding error messages and returns.
     * If validation succeeds, it calls the [loginUseCase] to initiate the login process.
     *
     * The function observes the login process and updates the state accordingly based on the result:
     * - If the login is successful ([Resource.Success]), it updates the state with the authenticated user.
     * - If an error occurs during login ([Resource.Error]), it updates the state with the error message.
     * - During the loading phase ([Resource.Loading]), it updates the state to indicate loading.
     *
     * @throws IllegalStateException if the [loginUseCase] is not provided or initialized.
     */
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

    /**
     * Handles events triggered in the authentication form, updating the state accordingly.
     *
     * This function takes an [AuthenticationFormEvent] as input and performs state modifications based on the event type:
     * - [AuthenticationFormEvent.UsernameChanged]: Updates the state with the new username.
     * - [AuthenticationFormEvent.EmailChanged]: Updates the state with the new email.
     * - [AuthenticationFormEvent.PasswordChanged]: Updates the state with the new password.
     * - [AuthenticationFormEvent.ConfirmPasswordChanged]: Updates the state with the new confirm password.
     * - [AuthenticationFormEvent.RegisterClicked]: Invokes the [registerUser] function to initiate user registration.
     *
     * @param event The [AuthenticationFormEvent] triggering the state update.
     */
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

    /**
     * Initiates the user registration process based on the current state's email, username, and password.
     *
     * This function performs validation on the provided email, username, and password using the specified validators.
     * If validation fails, it updates the state with corresponding error messages and returns.
     * If validation succeeds, it calls the [registerUseCase] to initiate the registration process.
     *
     * The function observes the registration process and updates the state accordingly based on the result:
     * - If the registration is successful ([Resource.Success]), it updates the state with the authenticated user.
     * - If an error occurs during registration ([Resource.Error]), it updates the state with the error message.
     * - During the loading phase ([Resource.Loading]), it updates the state to indicate loading.
     *
     * After the validation and registration process, the function sends a [ValidationEvent.Success] through the
     * [validationEventChannel] to notify the UI that the validation was successful.
     */
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
