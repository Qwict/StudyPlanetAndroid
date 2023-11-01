package com.qwict.studyplanetandroid.presentation.user

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.qwict.studyplanetandroid.data.local.DatabasePlanet
import com.qwict.studyplanetandroid.data.local.DatabaseUser

data class StudyPlanetUiState(
    var user: DatabaseUser = DatabaseUser(),
    val snackBarHostState: SnackbarHostState = SnackbarHostState(),
    var appJustLaunched: MutableState<Boolean> = mutableStateOf(true),
    var planets: List<DatabasePlanet> = emptyList(),
)
