package com.qwict.studyplanetandroid.common

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.qwict.studyplanetandroid.common.Constants.DEFAULT_UUID
import com.qwict.studyplanetandroid.service.tokenIsValid
import java.util.UUID

object AuthenticationSingleton {
    var isUserAuthenticated by mutableStateOf(false)
    var userUuid by mutableStateOf(DEFAULT_UUID)
    var experience by mutableStateOf(0)
    var token by mutableStateOf("")

    fun validateUser() {
        val token = getEncryptedPreference("token")
        userUuid = getUUID()
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

    fun logout() {
        isUserAuthenticated = false
        removeEncryptedPreference("token")
        removeEncryptedPreference("email")
    }

    fun getUUID(): UUID {
        userUuid = DEFAULT_UUID
        try {
//            userUuid = UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
//            saveEncryptedPreference("userUuid", userUuid.toString())
            userUuid = UUID.fromString(getEncryptedPreference("userUuid"))
        } catch (e: Exception) {
            Log.d("AuthenticationSingleton", "getUUID: userUuid was null, so we generated a new one: $userUuid")
            userUuid = UUID.randomUUID()
            saveEncryptedPreference("userUuid", userUuid.toString())
        }
        return userUuid
    }
}
