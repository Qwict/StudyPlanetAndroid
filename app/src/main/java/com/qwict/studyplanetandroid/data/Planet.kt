package com.qwict.studyplanetandroid.data

import android.util.Log

class Planet constructor(
    name: String? = "Unknown Planet",
    timeToExplore: Double? = 1.0,
    timeToDiscover: Double? = 1.0
) {
    val name = name
    val timeToExplore = timeToExplore
    val timeToDiscover = timeToDiscover

    init {
        Log.d("Planet", "Planet created with name: $name and timeToExplore: $timeToExplore and timeToDiscover: $timeToDiscover")
    }

}