package com.qwict.studyplanetandroid.common

import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.domain.model.User

object Constants {
    const val BASE_URL =
        "https://sp.qwict.com/api/"
//        "http://10.0.2.2:9012/api/" // For developing with android simulator
//        "http://192.168.1.32:9012/api/" // For developing with android device

    const val DEFAULT_STUDY_TIME = 1f

    val DEFAULT_PLANET_IMAGE_ID = R.drawable.galaxy
    val DEFAULT_PLANET_SMALL_IMAGE_ID = R.drawable.galaxy_small
    val EMPTY_USER =
        User(
            discoveredPlanets = emptyList(),
            email = "",
            name = "",
            experience = 0,
            currentLevel = 0,
            currentLevelProgress = 0,
            experienceForCurrentLevel = 0,
            experienceForNextLevel = 0,
            experienceProgress = 0.0f,
        )
    val EMPTY_PLANET =
        Planet(
            id = 0,
            name = "Galaxy",
        )
}
