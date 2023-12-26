package com.qwict.studyplanetandroid

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.presentation.screens.DiscoveredPlanetsScreen
import com.qwict.studyplanetandroid.presentation.viewmodels.states.UserState
import org.junit.Rule
import org.junit.Test

class DiscoveredPlanetsScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testDiscoveredPlanetsScreenUiState() {
        // Mock data
        val mockUserState = UserState(
            planets = listOf(
                Planet(id = 1, name = "Mercury"),
                Planet(id = 2, name = "Venus"),
                Planet(id = 3, name = "Earth"),
            ),
            isRefreshing = false,
            refreshError = "",
        )

        rule.setContent {
            MaterialTheme {
                DiscoveredPlanetsScreen(
                    navigateToTimeSelectionScreen = {},
                    getOnlinePlanets = { },
                    getLocalPlanets = { },
                    userState = mockUserState,
                    modifier = Modifier,
                )
            }
        }

        // Assertions
        rule.onNodeWithText("You have discovered 3 planets! These planets can now be mined for resources.")
            .assertIsDisplayed()

        rule.onNodeWithText("Mercury").assertIsDisplayed()
        rule.onNodeWithText("Venus").assertIsDisplayed()
        rule.onNodeWithText("Earth").assertIsDisplayed()
    }
}
