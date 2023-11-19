package com.qwict.studyplanetandroid.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.qwict.studyplanetandroid.common.AuthenticationSingleton

@Composable
fun AppBarAccountButton() {
    if (AuthenticationSingleton.isUserAuthenticated) {
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
