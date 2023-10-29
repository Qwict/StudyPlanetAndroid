package com.qwict.studyplanetandroid.presentation.user

import com.qwict.studyplanetandroid.data.Planet

data class UserUiState(
    var planets: List<Planet> = emptyList(),
)
