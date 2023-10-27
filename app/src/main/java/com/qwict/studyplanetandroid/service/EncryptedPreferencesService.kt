package com.qwict.svkandroid.helper

import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.qwict.studyplanetandroid.StudyPlanetApplication

// TODO: this is deprecated? What should we replace it with...
val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
// TODO: also declare sharedPreference variable here to make code more elegant?

fun getEncryptedPreference(key: String): String {
    val sharedPreferences = getSharedPreferences()
    return sharedPreferences.getString(key, "") ?: ""
}

fun getEncryptedPreferenceInteger(key: String): String {
    val sharedPreferences = getSharedPreferences()
    return sharedPreferences.getString(key, "") ?: "0"
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
