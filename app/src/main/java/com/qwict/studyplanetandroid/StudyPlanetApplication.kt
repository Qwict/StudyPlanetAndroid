package com.qwict.studyplanetandroid

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.filled.SatelliteAlt
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.isUserAuthenticated
import com.qwict.studyplanetandroid.data.AppContainer
import com.qwict.studyplanetandroid.data.AppDataContainer
import com.qwict.studyplanetandroid.ui.screens.AuthenticationScreen
import com.qwict.studyplanetandroid.ui.screens.DiscoveredPlanetsScreen
import com.qwict.studyplanetandroid.ui.screens.ExplorerScreen
import com.qwict.studyplanetandroid.ui.screens.MainScreen
import com.qwict.studyplanetandroid.ui.screens.TimeSelectionScreen
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

enum class StudyPlanetScreens(@StringRes val title: Int) {
    MainScreen(title = R.string.title_main_screen),
    AuthenticationScreen(title = R.string.title_authentication_screen),
    DiscoveredPlanetsScreen(title = R.string.title_discovered_planets_screen),
    TimeSelectionScreen(title = R.string.title_time_selection_screen),
    PlanetExplorerScreen(title = R.string.title_explorer_screen),
}

@HiltAndroidApp
class StudyPlanetApplication : Application() {
//    lateinit var container: AppContainer
    private lateinit var appScope: CoroutineScope
    override fun onCreate() {
        super.onCreate()
        appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
//        container = AppDataContainer(this, appScope)
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlanetAppBar(
    currentScreen: StudyPlanetScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    onAccountButtonClicked: () -> Unit = {},
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Return",
                    )
                }
            }
        },
        actions = {
//            if (userViewModel.userIsAuthenticated) {
//            TODO: This guy seems to cause a lot of rerenders..
//            ExperienceBar()
//            }
            Text(text = "user is authenticated: $isUserAuthenticated")
            AccountButton(onAccountButtonClicked)
        },

    )
}

@Composable
fun AccountButton(
    onAccountButtonClicked: () -> Unit,
) {
    if (isUserAuthenticated) {
        IconButton(onClick = { onAccountButtonClicked() }) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Your account screen",
            )
        }
    } else {
        IconButton(onClick = { onAccountButtonClicked() }) {
            Icon(
                imageVector = Icons.Filled.Login,
                contentDescription = "The login screen",
            )
        }
    }
}

@Composable
fun StudyPlanetNavBar(
    navController: NavHostController,
) {
    var selectedItem by remember { mutableStateOf(0) }
    data class NavbarItem(
        var text: String = "",
        var icon: ImageVector = Icons.Filled.Favorite,
        var contentDescription: String = "",
        var onClick: () -> Unit = {},
    )
    val items = listOf(
        NavbarItem(
            text = "Home",
            icon = Icons.Filled.Rocket,
            contentDescription = "Mission Control Center",
            onClick = { navController.navigate(StudyPlanetScreens.MainScreen.name) },
        ),
        NavbarItem(
            text = "Discover",
            icon = Icons.Filled.SatelliteAlt,
            contentDescription = "Discover a new planet",
            onClick = { navController.navigate(StudyPlanetScreens.TimeSelectionScreen.name) },
        ),
        NavbarItem(
            text = "Explore",
            icon = Icons.Filled.TravelExplore,
            contentDescription = "Explore a planet",
            onClick = { navController.navigate(StudyPlanetScreens.DiscoveredPlanetsScreen.name) },
        ),
    )

    NavigationBar {
        items.forEachIndexed { index, navbarItem ->
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = navbarItem.contentDescription) },
                label = { Text(navbarItem.text) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navbarItem.onClick()
                },
            )
            if (selectedItem == index) {
                Text(text = "Selected ${navbarItem.text}")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlanetApp(
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = StudyPlanetScreens.valueOf(
        backStackEntry?.destination?.route ?: StudyPlanetScreens.MainScreen.name,
    )

    Scaffold(
        topBar = {
            StudyPlanetAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                onAccountButtonClicked = {
                    navController.navigate(StudyPlanetScreens.AuthenticationScreen.name)
                },
            )
        },
        bottomBar = { StudyPlanetNavBar(navController) },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = StudyPlanetScreens.MainScreen.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = StudyPlanetScreens.MainScreen.name) {
                MainScreen(
                    onStartExploringButtonClicked = {
                        navController.navigate(
                            StudyPlanetScreens.DiscoveredPlanetsScreen.name,
                        )
                    },
                    onDiscoverPlanetsButtonClicked = {
                        navController.navigate(StudyPlanetScreens.TimeSelectionScreen.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                )
            }
            composable(route = StudyPlanetScreens.AuthenticationScreen.name) {
                AuthenticationScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                )
            }
            composable(route = StudyPlanetScreens.DiscoveredPlanetsScreen.name) {
                DiscoveredPlanetsScreen(
                    onMineButtonClicked = {
                        navController.navigate(StudyPlanetScreens.TimeSelectionScreen.name)
                    },
                    modifier = Modifier.fillMaxHeight(),
                )
            }
            composable(route = StudyPlanetScreens.TimeSelectionScreen.name) {
                TimeSelectionScreen(
                    onStartActionButtonClicked = {
                        navController.navigate(StudyPlanetScreens.PlanetExplorerScreen.name)
                    },
                    modifier = Modifier.fillMaxHeight(),
                )
            }
            composable(route = StudyPlanetScreens.PlanetExplorerScreen.name) {
                ExplorerScreen(
                    onCancelStudyButtonClicked = {
                        navController.navigate(StudyPlanetScreens.MainScreen.name)
                    },
                    modifier = Modifier.fillMaxHeight(),
                    isDiscovering = true,
                )
            }
        }
    }
}
