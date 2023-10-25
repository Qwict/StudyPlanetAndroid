package com.qwict.studyplanetandroid.data

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class StudyPlanetUiState(
    var user: User = User(),
    val snackBarHostState: SnackbarHostState = SnackbarHostState(),
    var userIsAuthenticated: MutableState<Boolean> = mutableStateOf(false),
    var appJustLaunched: MutableState<Boolean> = mutableStateOf(true),
    var selectedPlanet: Planet = Planet(),
    var planets: List<Planet> = emptyList(),
)
