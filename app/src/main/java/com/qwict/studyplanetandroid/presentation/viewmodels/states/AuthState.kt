package com.qwict.studyplanetandroid.presentation.viewmodels.states

import com.qwict.studyplanetandroid.common.Constants.EMPTY_USER
import com.qwict.studyplanetandroid.domain.model.User

data class AuthState(
    val isLoading: Boolean = false,
    val error: String = "",
    val user: User = EMPTY_USER,
    val appJustLaunched: Boolean = true,
    val registerNewUser: Boolean = false,
)
