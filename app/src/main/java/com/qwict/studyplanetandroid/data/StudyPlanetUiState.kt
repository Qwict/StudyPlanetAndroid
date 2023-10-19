package com.qwict.studyplanetandroid.data

data class StudyPlanetUiState(
    var selectedPlanet: PlanetEntity = PlanetEntity(),
    var planets: List<PlanetEntity> = emptyList(),
)
