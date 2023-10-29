package com.qwict.studyplanetandroid.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.auth0.android.jwt.JWT

data class DecodedUser(val jwt: String? = null) {

    private val TAG = "User"

    var id = 0
    var email = ""
    var token = ""
    var discoveredPlanets: MutableList<OldPlanet> = mutableListOf()
    var discoveredPlanetEntities: MutableList<Planet> = mutableListOf<Planet>()
    var experience = mutableStateOf(0)

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
