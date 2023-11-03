package com.qwict.studyplanetandroid.presentation.viewmodels.states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.qwict.studyplanetandroid.common.Constants.EMPTY_USER
import com.qwict.studyplanetandroid.domain.model.User

data class AuthState(
    val isLoading: Boolean = false,
    val error: String = "",
    val user: User = EMPTY_USER,
    val appJustLaunched: Boolean = true,
    var registerNewUser: MutableState<Boolean> = mutableStateOf(false),

    val experienceForNextLevel: Int = 0,
    val currentLevel: Int = 0,
    val experienceProgress: Float = 0f,
)
