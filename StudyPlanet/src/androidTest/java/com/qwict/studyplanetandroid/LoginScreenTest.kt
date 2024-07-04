package com.qwict.studyplanetandroid

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.qwict.studyplanetandroid.presentation.screens.auth.LoginScreen
import com.qwict.studyplanetandroid.presentation.viewmodels.states.AuthState
import kotlinx.coroutines.flow.flow
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Test
    fun testLoginScreen() {
        composeTestRule.setContent {
            LoginScreen(
                authState = AuthState(),
                loginUser = {},
                navigateToRegisterScreen = {},
                validationEvent = flow {},
                onEvent = {},
                switchPasswordVisibility = {},
                clearValidationErrors = {},
                windowSize = WindowSizeClass.calculateFromSize(DpSize(256.dp, 512.dp))
            )
        }

        composeTestRule.onNodeWithText("Email").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
        composeTestRule.onAllNodesWithText("Login")[1].assertHasClickAction()
        composeTestRule.onNodeWithText("Register").assertExists().assertHasClickAction()
    }
}
