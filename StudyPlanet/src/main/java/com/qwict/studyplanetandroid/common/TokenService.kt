package com.qwict.studyplanetandroid.common

import android.util.Log
import com.auth0.android.jwt.JWT

/**
 * Decodes a JSON Web Token (JWT) and extracts information from its payload.
 *
 * This function uses the [JWT] library to parse the JWT and retrieve specific claims from its payload.
 * It constructs and returns a [DecodedPayload] object containing the decoded information,
 * including email, remote ID, issued-at time (iat), and expiration time (exp).
 *
 * If any decoding error or null values are encountered, the function removes the token from preferences
 * and throws an [IllegalArgumentException] with an error message.
 *
 * @param token The JSON Web Token to decode and extract payload information from.
 * @return A [DecodedPayload] object containing decoded information from the JWT payload.
 * @throws IllegalArgumentException if the JWT is not valid or contains null values in required claims.
 */
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

/**
 * Checks the validity of a JSON Web Token (JWT) by examining its decoded payload.
 *
 * This function takes a JWT as input, decodes its payload using [getDecodedPayload],
 * and compares the expiration time (exp) claim with the current time to determine if the token is still valid.
 *
 * If the token is not empty, the function logs the decoded payload and checks if its expiration time
 * is greater than the current time. If valid, the function returns true; otherwise, it saves an "expired"
 * token preference and returns false.
 *
 * @param token The JSON Web Token to check for validity.
 * @return `true` if the token is valid, `false` otherwise.
 */
fun tokenIsValid(token: String): Boolean {
    if (token != "") {
        val decodedToken = getDecodedPayload(token)
        Log.i("MainActivity", "decoded token: $decodedToken")
        val tokenExpiration = decodedToken.exp
        val currentTime = System.currentTimeMillis() / 1000
        if (tokenExpiration.toLong() > currentTime) {
            Log.i("TokenService", "token still valid")
            return true
        }
    }
    saveEncryptedPreference("token", "expired")
    return false
}
