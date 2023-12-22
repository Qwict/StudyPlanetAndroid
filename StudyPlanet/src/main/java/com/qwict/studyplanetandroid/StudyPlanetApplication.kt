package com.qwict.studyplanetandroid

import android.app.Application
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.qwict.studyplanetandroid.common.AuthenticationSingleton
import com.qwict.studyplanetandroid.presentation.StudyPlanetNavigation
import com.qwict.studyplanetandroid.presentation.StudyPlanetScreens
import com.qwict.studyplanetandroid.presentation.components.nav.AppBar
import com.qwict.studyplanetandroid.presentation.components.nav.NavBar
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * The [Application] class for the StudyPlanet application.
 *
 * The [StudyPlanetApplication] class initializes the application and sets up the application context.
 */
@HiltAndroidApp
class StudyPlanetApplication : Application() {
    private lateinit var appScope: CoroutineScope

    /**
     * Called when the application is starting. Initializes the [CoroutineScope] and sets the application context.
     */
    override fun onCreate() {
        super.onCreate()
        appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        appContext = applicationContext
    }

    /**
     * The [Companion] object holds properties and functions that are shared across all instances of [StudyPlanetApplication].
     */
    companion object {
        // Might not be useful, because context is available everywhere... (looked cool tho)
        lateinit var appContext: Context
    }
}

/**
 * The main [Composable] function for the StudyPlanet application.
 *
 * @param navController The [NavHostController] used for navigation within the application.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlanetApp(
    navController: NavHostController = rememberNavController(),
) {
    // Retrieve the current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Determine the current screen based on the back stack entry
    val currentScreen = StudyPlanetScreens.valueOf(
        backStackEntry?.destination?.route ?: StudyPlanetScreens.MainScreen.name,
    )

    // Compose UI structure using Scaffold
    Scaffold(
        topBar = {
            // Display the app bar with relevant actions
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
            )
        },
        // Display the bottom navigation bar if the user is authenticated
        bottomBar = { if (AuthenticationSingleton.isUserAuthenticated) { NavBar(currentScreen, navController) } },
    ) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {
            StudyPlanetNavigation(navController = navController)
        }
    }
}