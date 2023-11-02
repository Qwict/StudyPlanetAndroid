package com.qwict.studyplanetandroid.presentation.user

import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.domain.model.User

data class UserState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val refreshError: String = "",
    val error: String = "",
    val user: User? = null,
    val planets: List<Planet> = emptyList(),
)
