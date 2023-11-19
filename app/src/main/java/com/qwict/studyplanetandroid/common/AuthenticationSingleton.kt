package com.qwict.studyplanetandroid.common

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object AuthenticationSingleton {
    var isUserAuthenticated by mutableStateOf(false)
        private set
    var experience by mutableIntStateOf(0)

    fun validateUser() {
        val token = getEncryptedPreference("token")
        if (token != null && token != "") {
            if (tokenIsValid(getEncryptedPreference("token"))) {
                isUserAuthenticated = true
            } else {
                Log.i("EncryptionService", "onResume: token was found but is invalid")
                logout()
            }
        } else {
            Log.i("EncryptionService", "onResume: token is null or empty")
            logout()
        }
    }

    fun getRemoteId(): Int {
        if (!isUserAuthenticated) {
            throw IllegalArgumentException("User is not authenticated")
        }
        return getDecodedPayload(getEncryptedPreference("token")!!).remoteId
    }

    fun logout() {
        isUserAuthenticated = false
        removeEncryptedPreference("token")
        removeEncryptedPreference("email")
    }
}
