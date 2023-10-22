package com.qwict.studyplanetandroid.dto

import android.util.Log
import com.auth0.android.jwt.JWT
import com.qwict.studyplanetandroid.data.Planet
import com.qwict.studyplanetandroid.data.PlanetEntity

data class User(val jwt: String? = null) {

    private val TAG = "User"

    var id = 0
    var email = ""
    var token = ""
    var discoveredPlanets: MutableList<Planet> = mutableListOf<Planet>()
    var discoveredPlanetEntities: MutableList<PlanetEntity> = mutableListOf<PlanetEntity>()
    var experience = 0
    var discoveredPlanets: MutableList<Planet> = mutableListOf()

    init {
        if (jwt != null) {
            try {
                val jwt = JWT(jwt ?: "")
                id = jwt.getClaim("id").asInt()!!
                email = jwt.getClaim("email").asString() ?: ""
                token = this.jwt
            } catch (error: com.auth0.android.jwt.DecodeException) {
                Log.e(TAG, "Error occurred trying to decode JWT: $error ")
            }
        } else {
            Log.d(TAG, "User is logged out - instantiating empty User object.")
        }
    }
}
