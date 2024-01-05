package com.qwict.studyplanetandroid.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.presentation.components.ExperienceBar
import com.qwict.studyplanetandroid.presentation.components.Loader
import com.qwict.studyplanetandroid.presentation.viewmodels.MainViewModel
import com.qwict.studyplanetandroid.presentation.viewmodels.states.MainScreenState

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel(),
    windowSize: WindowSizeClass,
) {
    LaunchedEffect(mainViewModel) {
        mainViewModel.getActiveUser()
    }

    when (mainViewModel.state) {
        is MainScreenState.Loading -> {
            Loader()
        }
        is MainScreenState.Error -> {
            Text(text = "Error")
        }
        is MainScreenState.Success -> {
            val user by (mainViewModel.state as MainScreenState.Success).user.collectAsState()
            when (windowSize.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.study_planet_icon),
                            contentDescription = "The logo of Study Planet",
                        )
                        Text(
                            text = "Welcome back, ${user.name}!",
                            style = MaterialTheme.typography.headlineLarge,
                        )
                        Text(
                            text = "Level ${user.currentLevel}",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(20.dp),
                        ) {
                            ExperienceBar(
                                experience = user.experience,
                                currentLevelProgress = user.currentLevelProgress,
                                experienceForNextLevel = user.experienceForNextLevel,
                                experienceProgress = user.experienceProgress,
                            )
                        }
                    }
                }
                else -> {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.study_planet_icon),
                            contentDescription = "The logo of Study Planet",
                            modifier.weight(1f),
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(20.dp).weight(1f),
                        ) {
                            Text(
                                text = "Welcome back, ${user.name}!",
                                style = MaterialTheme.typography.headlineLarge,
                            )
                            Text(
                                text = "Level ${user.currentLevel}",
                                style = MaterialTheme.typography.headlineMedium,
                            )
                            Column {
                                ExperienceBar(
                                    experience = user.experience,
                                    currentLevelProgress = user.currentLevelProgress,
                                    experienceForNextLevel = user.experienceForNextLevel,
                                    experienceProgress = user.experienceProgress,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
