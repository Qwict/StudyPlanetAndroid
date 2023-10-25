package com.qwict.svkandroid.helper

import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.qwict.studyplanetandroid.StudyPlanetApplication
import com.qwict.studyplanetandroid.data.DecodedUser
import com.qwict.studyplanetandroid.service.tokenIsValid
import com.qwict.studyplanetandroid.ui.viewModels.MainViewModel

// TODO: this is deprecated? What should we replace it with...
val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
// TODO: also declare sharedPreference variable here to make code more elegant?

fun getTokenFromSharedPrefs(mainViewModel: MainViewModel) {
    val sharedPreferences = getSharedPreferences()
    val token = sharedPreferences.getString("token", "")

    if (token != null && token != "") {
        if (tokenIsValid(mainViewModel)) {
            Log.i("MainActivity", "onResume: token was found")
            mainViewModel.decodedUser = DecodedUser(
                jwt = token,
            )
            mainViewModel.userIsAuthenticated.value = true
        } else {
            Log.i("MainActivity", "onResume: token was found but is invalid")
            mainViewModel.logout()
        }
    } else {
        Log.i("MainActivity", "onResume: token is null or empty")
        mainViewModel.logout()
    }
}

fun getEncryptedPreference(key: String): String {
    val sharedPreferences = getSharedPreferences()
    return sharedPreferences.getString(key, "") ?: ""
}
fun saveEncryptedPreference(key: String, preference: String) {
    val sharedPreferences = getSharedPreferences()
    sharedPreferences.edit().putString(key, preference).apply()

    // TODO remove in production
    Log.i("MainActivity", "onResume: $key was saved to preference file as: '$preference'")
}

fun getSharedPreferences(): SharedPreferences {
    return EncryptedSharedPreferences.create(
        "preferences",
        masterKeyAlias,
        StudyPlanetApplication.appContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )
}

fun clearEncryptedPreferences(key: String) {
    val sharedPreferences = getSharedPreferences()
    sharedPreferences.edit().clear().apply()
}
