package com.qwict.studyplanetandroid.presentation.viewmodels.states

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity

data class StudyPlanetUiState(
    var user: UserRoomEntity = UserRoomEntity(),
    val snackBarHostState: SnackbarHostState = SnackbarHostState(),
    var appJustLaunched: MutableState<Boolean> = mutableStateOf(true),
    var planets: List<PlanetRoomEntity> = emptyList(),
)
