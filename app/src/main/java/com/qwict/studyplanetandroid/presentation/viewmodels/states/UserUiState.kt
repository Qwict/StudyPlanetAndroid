package com.qwict.studyplanetandroid.presentation.viewmodels.states

import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity

data class UserUiState(
    var planets: List<PlanetRoomEntity> = emptyList(),
)
