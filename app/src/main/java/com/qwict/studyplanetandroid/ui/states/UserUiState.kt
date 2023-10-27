package com.qwict.studyplanetandroid.ui.states

import com.qwict.studyplanetandroid.data.Planet

data class UserUiState(
    var planets: List<Planet> = emptyList(),
)
