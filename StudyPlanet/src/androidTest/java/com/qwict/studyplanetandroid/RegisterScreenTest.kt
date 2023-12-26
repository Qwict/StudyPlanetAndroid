package com.qwict.studyplanetandroid

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.qwict.studyplanetandroid.presentation.screens.auth.RegisterScreen
import com.qwict.studyplanetandroid.presentation.viewmodels.states.AuthState
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class RegisterScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testRegisterScreenCorrectInput() {
        var registerUserCalled = false
        var authState = AuthState()
        rule.setContent {
            RegisterScreen(
                authState = authState,
                registerUser = {
                    registerUserCalled = true
                },
                navigateToLoginScreen = {},
                validationEvent = flowOf(),
                onEvent = {},
                switchPasswordVisibility = {
                    authState = authState.copy(
                        isPasswordVisible = !authState.isPasswordVisible,
                    )
                },
                clearValidationErrors = {},
            )
        }

        rule.onNodeWithText("Email").assertExists()
        rule.onNodeWithText("Email").performTextInput("test@example.com")
        rule.onNodeWithText("Password").performTextInput("password123!")
        rule.onNodeWithText("Confirm Password").performTextInput("password123!")

        rule.onAllNodesWithContentDescription("Show password")[0].assertHasClickAction().performClick()
        Assert.assertTrue(authState.isPasswordVisible)

        rule.onNodeWithText("Register").performClick()
        Assert.assertTrue(registerUserCalled)
    }
}
