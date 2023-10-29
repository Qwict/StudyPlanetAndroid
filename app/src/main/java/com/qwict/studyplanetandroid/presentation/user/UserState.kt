package com.qwict.studyplanetandroid.presentation.user

import com.qwict.studyplanetandroid.domain.model.User

data class UserState(
    val isLoading: Boolean = false,
    val error: String = "",
    val user: User? = null,
)
