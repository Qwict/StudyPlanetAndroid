package com.qwict.studyplanetandroid.common

import android.util.Log
import androidx.compose.runtime.getValue
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

    /**
     * Validates user authentication status based on the presence and validity of the stored token.
     * If the token is valid, sets [isUserAuthenticated] to true; otherwise, logs out the user.
     */
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

    /**
     * Retrieves the remote ID of the authenticated user.
     *
     * @return The remote ID of the authenticated user.
     * @throws IllegalArgumentException if the user is not authenticated.
     */
    fun getRemoteId(): Int {
        if (!isUserAuthenticated) {
            throw IllegalArgumentException("User is not authenticated")
        }
        return getDecodedPayload(getEncryptedPreference("token")!!).remoteId
    }

    /**
     * Logs out the user by resetting the authentication status and removing stored credentials.
     */
    fun logout() {
        isUserAuthenticated = false
        removeEncryptedPreference("token")
        removeEncryptedPreference("email")
    }
}
