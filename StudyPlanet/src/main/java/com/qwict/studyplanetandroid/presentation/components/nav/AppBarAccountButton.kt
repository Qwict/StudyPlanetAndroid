package com.qwict.studyplanetandroid.presentation.components.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.qwict.studyplanetandroid.common.AuthenticationSingleton
import com.qwict.studyplanetandroid.presentation.StudyPlanetScreens

@Composable
fun AppBarAccountButton(currentScreen: StudyPlanetScreens) {
    if (AuthenticationSingleton.isUserAuthenticated && currentScreen == StudyPlanetScreens.MainScreen) {
        IconButton(
            onClick = { AuthenticationSingleton.logout() },
        ) {
            Icon(
                imageVector = Icons.Filled.Logout,
                contentDescription = "Logout",
            )
        }
    }
}
