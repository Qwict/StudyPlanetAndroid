package com.qwict.studyplanetandroid.common

import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.domain.model.User
import java.util.UUID

object Constants {
    const val BASE_URL =
        "http://192.168.1.39:9010/"
//        "http://10.0.2.2:9010/"

    val DEFAULT_UUID = UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
    const val DEFAULT_ID = 0
    val DEFAULT_PLANET_IMAGE_ID = R.drawable.earth
    val EMPTY_USER = User(
        discoveredPlanets = emptyList(),
        email = "",
        experience = 0,
        id = 0,
        name = "",
    )
    val EMPTY_PLANET = Planet(
        id = 0,
        name = "Unknown Planet",
        imageId = R.drawable.earth,
    )
}
