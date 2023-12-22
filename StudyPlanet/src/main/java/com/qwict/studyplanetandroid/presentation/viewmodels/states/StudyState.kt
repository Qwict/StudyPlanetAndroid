package com.qwict.studyplanetandroid.presentation.viewmodels.states

import com.qwict.studyplanetandroid.common.Constants.EMPTY_PLANET
import com.qwict.studyplanetandroid.domain.model.Planet

data class StudyState(
    var selectedTime: Int = 0,
    var selectedPlanet: Planet = EMPTY_PLANET,

    var discoveredPlanet: Planet = EMPTY_PLANET,
    var hasDiscoveredPlanet: Boolean = false,

    var hours: Int = 0,
    var minutes: Int = 0,
    var seconds: Int = 0,
    var updatedTime: Int = 0,
    var currentProgress: Float = 0f,

    var isLoading: Boolean = false,
    var error: String = "",

    val openOnBackAlertDialog: Boolean = false,
    val openPlanetDiscoveredDialog: Boolean = false,
)
