package com.qwict.studyplanetandroid.presentation.viewmodels.states

import com.qwict.studyplanetandroid.common.Constants.EMPTY_USER
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.domain.model.User

data class UserState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val refreshError: String = "",
    val error: String = "",
    val user: User = EMPTY_USER,
    val planets: List<Planet> = emptyList(),

    val experienceForNextLevel: Int = 0,
    val currentLevel: Int = 0,
    val experienceProgress: Float = 0f,
)
