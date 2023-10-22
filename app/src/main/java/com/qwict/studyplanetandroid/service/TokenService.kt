package com.qwict.studyplanetandroid.service

import android.util.Base64
import android.util.Log
import com.qwict.studyplanetandroid.ui.viewModels.MainViewModel

fun decodeToken(jwt: String): String {
    val (header, payload, signature) = jwt.split(".")

    return try {
        Base64.decode(header, Base64.DEFAULT).decodeToString()
        Base64.decode(payload, Base64.DEFAULT).decodeToString()
    } catch (e: Exception) {
        "Error parsing JWT: $e"
    }
}

fun tokenIsValid(mainViewModel: MainViewModel): Boolean {
    if (mainViewModel.user.token != "") {
        val decodedToken = decodeToken(mainViewModel.user.token)
        Log.i("MainActivity", "decoded token: $decodedToken")

        val tokenExpiration = decodedToken
            .substringAfter("exp\":")
            .substringBefore(",")
//            if exp is in the end of the decoded token the before , will not work, so we need to remove the last }
            .replace("}", "")
        val currentTime = System.currentTimeMillis() / 1000
        if (tokenExpiration.toLong() < currentTime) {
            mainViewModel.user.token = "expired"
            Log.i("MainActivity", "token expired")
            return false
        }
    }
    return true
}
