package com.qwict.studyplanetandroid.common

import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.domain.model.User

object Constants {
    const val BASE_URL = "https://sp.qwict.com/api/"
//        "http://10.0.2.2:9012/api/"
//        "http://192.168.1.33:9012/api/"

    val DEFAULT_PLANET_IMAGE_ID = R.drawable.galaxy
    val EMPTY_USER = User(
        discoveredPlanets = emptyList(),
        email = "",
        experience = 0,
        id = 0,
        name = "",
    )
    val EMPTY_PLANET = Planet(
        id = 0,
        name = "Galaxy",
    )
}
