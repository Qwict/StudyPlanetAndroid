package com.qwict.studyplanetandroid.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.filled.SatelliteAlt
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.qwict.studyplanetandroid.StudyPlanetScreens
import com.qwict.studyplanetandroid.presentation.viewmodels.MainViewModel

@Composable
fun NavBar(
    currentScreen: StudyPlanetScreens,
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    data class NavbarItem(
        var text: String = "",
        var name: String = "",
        var icon: ImageVector = Icons.Filled.Favorite,
        var contentDescription: String = "",
        var onClick: () -> Unit = {},
    )

    var selectedNavbarItemIndex by remember { mutableStateOf(0) }

    val items = listOf(
        NavbarItem(
            text = "Home",
            name = StudyPlanetScreens.MainScreen.name,
            icon = Icons.Filled.Rocket,
            contentDescription = "Mission Control Center",
            onClick = { navController.navigate(StudyPlanetScreens.MainScreen.name) },
        ),
        NavbarItem(
            text = "Discover",
            name = StudyPlanetScreens.TimeSelectionScreen.name,
            icon = Icons.Filled.SatelliteAlt,
            contentDescription = "Discover a new planet",
            onClick = {
                mainViewModel.isDiscovering = true
                navController.navigate(StudyPlanetScreens.TimeSelectionScreen.name)
            },
        ),
        NavbarItem(
            text = "Explore",
            name = StudyPlanetScreens.DiscoveredPlanetsScreen.name,
            icon = Icons.Filled.TravelExplore,
            contentDescription = "Explore a planet",
            onClick = { navController.navigate(StudyPlanetScreens.DiscoveredPlanetsScreen.name) },
        ),
    )

    NavigationBar {
//        The user should not be allowed to leave the study screen by clicking the navbar
        if (currentScreen.name != StudyPlanetScreens.PlanetExplorerScreen.name) {
            items.forEachIndexed { index, navbarItem ->
                NavigationBarItem(
                    icon = { Icon(navbarItem.icon, contentDescription = navbarItem.contentDescription) },
                    label = { if (currentScreen.name == navbarItem.name) Text(navbarItem.text) },
                    selected = currentScreen.name == navbarItem.name,
                    onClick = {
                        selectedNavbarItemIndex = index
                        navbarItem.onClick()
                    },
                )
            }
        }
    }
}