package com.qwict.studyplanetandroid.service

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.qwict.svkandroid.helper.clearEncryptedPreferences
import com.qwict.svkandroid.helper.getEncryptedPreference

object AuthenticationSingleton {
    var isUserAuthenticated by mutableStateOf(false)

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

    fun logout() {
        isUserAuthenticated = false
        clearEncryptedPreferences("token")
        clearEncryptedPreferences("userId")
        clearEncryptedPreferences("email")
    }

}
