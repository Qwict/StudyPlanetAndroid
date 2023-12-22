package com.qwict.studyplanetandroid.presentation.viewmodels.sealed

sealed class AuthenticationFormEvent {
    data class UsernameChanged(val username: String) : AuthenticationFormEvent()
    data class EmailChanged(val email: String) : AuthenticationFormEvent()
    data class PasswordChanged(val password: String) : AuthenticationFormEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : AuthenticationFormEvent()
    data object RegisterClicked : AuthenticationFormEvent()
}
