package com.qwict.studyplanetandroid

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.qwict.studyplanetandroid.presentation.SnackbarAppState
import com.qwict.studyplanetandroid.presentation.StudyPlanetNavigation
import com.qwict.studyplanetandroid.presentation.components.AppBar
import com.qwict.studyplanetandroid.presentation.components.NavBar
import com.qwict.studyplanetandroid.presentation.rememberSnackbarAppState
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

enum class StudyPlanetScreens(@StringRes val title: Int) {
    StartScreen(title = R.string.title_start_screen),
    MainScreen(title = R.string.title_main_screen),
    AuthenticationScreen(title = R.string.title_authentication_screen),
    DiscoveredPlanetsScreen(title = R.string.title_discovered_planets_screen),
    TimeSelectionScreen(title = R.string.title_time_selection_screen),
    PlanetExplorerScreen(title = R.string.title_explorer_screen),
}

@HiltAndroidApp
class StudyPlanetApplication : Application() {
    private lateinit var appScope: CoroutineScope
    override fun onCreate() {
        super.onCreate()
        appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlanetApp(
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val appState: SnackbarAppState = rememberSnackbarAppState()
    val currentScreen = StudyPlanetScreens.valueOf(
        backStackEntry?.destination?.route ?: StudyPlanetScreens.MainScreen.name,
    )

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                onAccountButtonClicked = {
                    navController.navigate(StudyPlanetScreens.AuthenticationScreen.name)
                },
            )
        },
        bottomBar = { NavBar(currentScreen, navController) },
    ) { innerPadding ->
        StudyPlanetNavigation(
            innerPadding,
            navController = navController,
            showSnackbar = { message, duration ->
                appState.showSnackbar(message = message, duration = duration)
            },
        )
    }
}
