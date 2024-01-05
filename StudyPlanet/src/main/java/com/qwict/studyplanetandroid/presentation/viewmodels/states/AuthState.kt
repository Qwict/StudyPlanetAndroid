package com.qwict.studyplanetandroid.presentation.viewmodels.states

data class AuthState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val usernameError: String = "",
    val emailError: String = "",
    val passwordError: String = "",
    val confirmPasswordError: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
    val appJustLaunched: Boolean = true,
    var registerNewUser: Boolean = false,
    val experienceForNextLevel: Int = 0,
    val currentLevel: Int = 0,
    val experienceProgress: Float = 0f,
)
