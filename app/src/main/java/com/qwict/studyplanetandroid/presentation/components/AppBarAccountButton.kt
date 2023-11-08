package com.qwict.studyplanetandroid.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Login
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.qwict.studyplanetandroid.StudyPlanetScreens
import com.qwict.studyplanetandroid.common.AuthenticationSingleton

@Composable
fun AppBarAccountButton(
    onAccountButtonClicked: () -> Unit,
    currentScreen: StudyPlanetScreens,
) {
    val isAuthScreenSelected = currentScreen.name != StudyPlanetScreens.AuthenticationScreen.name
    if (AuthenticationSingleton.isUserAuthenticated) {
        IconButton(
            onClick = { onAccountButtonClicked() },
            enabled = isAuthScreenSelected,
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Your account screen",
            )
        }
    } else {
        IconButton(
            onClick = { onAccountButtonClicked() },
            enabled = isAuthScreenSelected,
        ) {
            Icon(
                imageVector = Icons.Filled.Login,
                contentDescription = "The login screen",
            )
        }
    }
}