package com.qwict.studyplanetandroid.presentation.viewmodels.states

import com.qwict.studyplanetandroid.common.Constants.EMPTY_PLANET
import com.qwict.studyplanetandroid.domain.model.Planet

data class StudyState(
    val selectedTime: Int = 0,
    val selectedPlanet: Planet = EMPTY_PLANET,
    var discoveredPlanet: Planet = EMPTY_PLANET,
    var hasDiscoveredPlanet: Boolean = false,
    val hours: Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0,
    val updatedTime: Int = 0,
    val progressPercentage: Int = 0,
    var currentProgress: Float = 0.2f,
    var isLoading: Boolean = false,
    var error: String = "",
    val openOnBackAlertDialog: Boolean = false,
    val openPlanetDiscoveredDialog: Boolean = false,
)
