package com.qwict.studyplanetandroid.common

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.qwict.studyplanetandroid.service.tokenIsValid
import com.qwict.svkandroid.helper.clearEncryptedPreferences
import com.qwict.svkandroid.helper.getEncryptedPreference
import com.qwict.svkandroid.helper.saveEncryptedPreference
import java.util.UUID

object AuthenticationSingleton {
    var isUserAuthenticated by mutableStateOf(false)
    var userUuid by mutableStateOf(getUUID())
    var experience by mutableStateOf(0)
    var token by mutableStateOf("")

    fun validateUser() {
        val token = getEncryptedPreference("token")
        experience = getEncryptedPreference("experience").toInt()
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
        clearEncryptedPreferences("token")
        clearEncryptedPreferences("userId")
        clearEncryptedPreferences("email")
    }

    fun getUUID(): UUID {
        var userUuid = UUID.fromString(getEncryptedPreference("userUuid"))
        if (userUuid == null || userUuid.toString() == "") {
            userUuid = UUID.randomUUID()
            Log.d("AuthenticationSingleton", "getUUID: userUuid was null, so we generated a new one: $userUuid")
            saveEncryptedPreference("userUuid", userUuid.toString())
        } else {
            Log.d("AuthenticationSingleton", "getUUID: userUuid existing one: $userUuid")
        }

        return userUuid
    }
}
