package com.qwict.studyplanetandroid.common

import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.domain.model.User

object Constants {
    const val BASE_URL =
        "http://192.168.1.36:9012/api/"
//        "https://sp.qwict.com/api/"
//        "http://10.0.2.2:9012/api/"
//        "http://192.168.0.224:9012/api/"

    val DEFAULT_PLANET_IMAGE_ID = R.drawable.galaxy
    val DEFAULT_PLANET_SMALL_IMAGE_ID = R.drawable.galaxy_small
    val EMPTY_USER = User(
        discoveredPlanets = emptyList(),
        email = "",
        experience = 0,
        name = "",
        currentLevel = 0,
        experienceForCurrentLevel = 0,
        experienceForNextLevel = 0,
        experienceProgress = 0.0f,
    )
    val EMPTY_PLANET = Planet(
        id = 0,
        name = "Galaxy",
    )
}
