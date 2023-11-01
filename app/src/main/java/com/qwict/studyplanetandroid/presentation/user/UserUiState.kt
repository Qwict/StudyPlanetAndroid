package com.qwict.studyplanetandroid.presentation.user

import com.qwict.studyplanetandroid.data.local.DatabasePlanet

data class UserUiState(
    var planets: List<DatabasePlanet> = emptyList(),
)
