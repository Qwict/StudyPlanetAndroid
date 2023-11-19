package com.qwict.studyplanetandroid.presentation.viewmodels.states

import com.qwict.studyplanetandroid.common.Constants.EMPTY_USER
import com.qwict.studyplanetandroid.domain.model.User

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
    val user: User = EMPTY_USER,
    val appJustLaunched: Boolean = true,
    var registerNewUser: Boolean = false,

    val experienceForNextLevel: Int = 0,
    val currentLevel: Int = 0,
    val experienceProgress: Float = 0f,
)
