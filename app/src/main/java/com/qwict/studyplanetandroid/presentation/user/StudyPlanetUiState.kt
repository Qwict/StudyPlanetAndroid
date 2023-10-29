package com.qwict.studyplanetandroid.presentation.user

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.qwict.studyplanetandroid.data.Planet
import com.qwict.studyplanetandroid.data.User

data class StudyPlanetUiState(
    var user: User = User(),
    val snackBarHostState: SnackbarHostState = SnackbarHostState(),
    var appJustLaunched: MutableState<Boolean> = mutableStateOf(true),
    var planets: List<Planet> = emptyList(),
)
