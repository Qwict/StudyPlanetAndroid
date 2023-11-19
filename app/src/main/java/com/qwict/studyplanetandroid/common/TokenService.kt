package com.qwict.studyplanetandroid.common

import android.util.Base64
import android.util.Log
import com.auth0.android.jwt.JWT

fun decodeToken(jwt: String): String {
    val (header, payload, signature) = jwt.split(".")

    return try {
        Base64.decode(header, Base64.DEFAULT).decodeToString()
        Base64.decode(payload, Base64.DEFAULT).decodeToString()
    } catch (e: Exception) {
        "Error parsing JWT: $e"
    }
}

fun getDecodedPayload(token: String): DecodedPayload {
    val jwt = JWT(token)
    try {
        return DecodedPayload(
            email = jwt.getClaim("email").asString()!!,
            remoteId = jwt.getClaim("id").asInt()!!,
            iat = jwt.getClaim("iat").asInt()!!,
            exp = jwt.getClaim("exp").asInt()!!,
        )
    } catch (e: NullPointerException) {
        removeEncryptedPreference("token")
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
