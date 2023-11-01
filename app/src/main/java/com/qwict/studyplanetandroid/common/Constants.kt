package com.qwict.studyplanetandroid.common

import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.domain.model.User
import java.util.UUID

object Constants {
//        "http://192.168.1.39:9010/v1/"
    const val BASE_URL = "http://10.0.2.2:9010/"

    val DEFAULT_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000")
    const val DEFAULT_ID = 0
    val EMPTY_USER = User(
        discoveredPlanets = emptyList(),
        email = "",
        experience = 0,
        id = 0,
        name = "",
    )
    val EMPTY_PLANET = Planet(
        id = 0,
        name = "",
        imageId = R.drawable.earth,
        userOwnerId = 0,
        userUuid = DEFAULT_UUID,
    )
}
