package com.qwict.studyplanetandroid.common

import android.util.Base64
import android.util.Log
import com.auth0.android.jwt.JWT
import java.util.UUID

fun decodeToken(jwt: String): String {
    val (header, payload, signature) = jwt.split(".")

    return try {
        Base64.decode(header, Base64.DEFAULT).decodeToString()
        Base64.decode(payload, Base64.DEFAULT).decodeToString()
    } catch (e: Exception) {
        "Error parsing JWT: $e"
    }
}

fun getDecodedUserFromToken(token: String): DecodedUser {
    val jwt = JWT(token)
    try {
        return DecodedUser(
            id = jwt.getClaim("id").asInt()!!,
            uuid = UUID.fromString(jwt.getClaim("uuid").asString()),
            name = jwt.getClaim("name").asString()!!,
            email = jwt.getClaim("email").asString()!!,
        )
    } catch (e: NullPointerException) {
        throw IllegalArgumentException("JWT is not valid")
    }
}

fun tokenIsValid(token: String): Boolean {
    if (token != "") {
        val decodedToken = decodeToken(token)
        Log.i("MainActivity", "decoded token: $decodedToken")

        val tokenExpiration = decodedToken
            .substringAfter("exp\":")
            .substringBefore(",")
//            if exp is in the end of the decoded token the before , will not work, so we need to remove the last }
            .replace("}", "")
        val currentTime = System.currentTimeMillis() / 1000
        if (tokenExpiration.toLong() > currentTime) {
            Log.i("MainActivity", "token still valid")
            return true
        }
    }
    saveEncryptedPreference("token", "expired")
    return false
}
