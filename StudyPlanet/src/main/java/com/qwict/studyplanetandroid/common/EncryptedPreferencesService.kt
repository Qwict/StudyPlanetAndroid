package com.qwict.studyplanetandroid.common

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.qwict.studyplanetandroid.StudyPlanetApplication

// TODO: this is deprecated? What should we replace it with...
val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
// TODO: also declare sharedPreference variable here to make code more elegant?

/**
 * Retrieves an encrypted preference value associated with the given [key].
 *
 * This function fetches an encrypted preference value stored in the SharedPreferences.
 * It uses the [getSharedPreferences] function to access the SharedPreferences instance.
 * If the preference value for the specified [key] is not found, an empty string is returned.
 *
 * @param key The key associated with the preference value to retrieve.
 *
 * @return The encrypted preference value for the given [key] or an empty string if not found.
 */
fun getEncryptedPreference(key: String): String {
    return getSharedPreferences().getString(key, "") ?: ""
}

/**
 * Saves an encrypted preference value for the given [key].
 *
 * This function stores the provided [preference] as an encrypted value in the SharedPreferences
 * using the specified [key]. It uses the [getSharedPreferences] function to access the SharedPreferences instance.
 *
 * @param key The key under which the preference value will be stored.
 * @param preference The encrypted preference value to be saved.
 */
fun saveEncryptedPreference(key: String, preference: String) {
    getSharedPreferences().edit().putString(key, preference).apply()
}

/**
 * Removes the encrypted preference value associated with the given [key].
 *
 * This function removes the encrypted preference value stored in the SharedPreferences
 * for the specified [key]. It uses the [getSharedPreferences] function to access the SharedPreferences instance.
 *
 * @param key The key associated with the preference value to be removed.
 */
fun removeEncryptedPreference(key: String) {
    val sharedPreferences = getSharedPreferences()
    sharedPreferences.edit().remove(key).apply()
}

/**
 * Retrieves the encrypted SharedPreferences instance used for storing sensitive data.
 *
 * This function creates and returns an instance of [EncryptedSharedPreferences] with the specified configurations.
 * It uses the provided master key alias, application context from [StudyPlanetApplication], and encryption schemes
 * for both preference keys and values.
 *
 * @return An [EncryptedSharedPreferences] instance for securely storing sensitive data.
 */
private fun getSharedPreferences(): SharedPreferences {
    return EncryptedSharedPreferences.create(
        "preferences",
        masterKeyAlias,
        StudyPlanetApplication.appContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )
}
