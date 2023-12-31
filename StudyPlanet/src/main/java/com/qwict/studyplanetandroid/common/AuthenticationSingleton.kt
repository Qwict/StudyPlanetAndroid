package com.qwict.studyplanetandroid.common

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import javax.inject.Singleton

/**
 * Singleton object responsible for managing user authentication state and related operations.
 *
 * The [AuthenticationSingleton] object provides properties and methods to track user authentication status,
 * validate user credentials, retrieve the user's remote ID, and handle user logout.
 *
 * @property isUserAuthenticated Indicates whether the user is currently authenticated.
 */
@Singleton
object AuthenticationSingleton {
    var isUserAuthenticated by mutableStateOf(false)
        private set
    var remoteUserId by mutableIntStateOf(0)
        private set

    /**
     * Validates user authentication status based on the presence and validity of the stored token.
     * If the token is valid, sets [isUserAuthenticated] to true; otherwise, logs out the user.
     */
    fun validateUser() {
        val token = getEncryptedPreference("token")
        if (token != "" && token != "expired") {
            if (tokenIsValid(getEncryptedPreference("token"))) {
                isUserAuthenticated = true
                remoteUserId = getDecodedPayload(getEncryptedPreference("token")).remoteId
            } else {
                Log.i("EncryptionService", "onResume: token was found but is invalid")
                logout()
            }
        } else {
            Log.i("EncryptionService", "onResume: token is null or empty")
            logout()
        }
    }

    /**
     * Logs out the user by resetting the authentication status and removing stored credentials.
     */
    fun logout() {
        isUserAuthenticated = false
        remoteUserId = 0
        removeEncryptedPreference("token")
        removeEncryptedPreference("email")
    }
}
