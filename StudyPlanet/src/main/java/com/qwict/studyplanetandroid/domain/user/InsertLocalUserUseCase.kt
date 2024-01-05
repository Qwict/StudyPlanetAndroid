package com.qwict.studyplanetandroid.domain.user

import com.qwict.studyplanetandroid.StudyPlanetApplication
import com.qwict.studyplanetandroid.common.saveEncryptedPreference

fun saveTokenAndValidateUserUseCase(token: String) {
    // Shared Preferences Part
    saveEncryptedPreference("token", token)

    // Singleton Part, validation should work because token was saved in shared preferences
    StudyPlanetApplication.authSingleton.validateUser()
}
