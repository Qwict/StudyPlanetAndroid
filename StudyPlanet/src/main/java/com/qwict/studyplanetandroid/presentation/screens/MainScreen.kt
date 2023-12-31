package com.qwict.studyplanetandroid.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.study_planet_icon),
                    contentDescription = "Logo",
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
                        experienceForNextLevel = user.experienceForNextLevel,
                        experienceProgress = user.experienceProgress,
                    )
                }
            }
        }
    }
}

@Composable
fun UserInfoRow(
    label: String,
    value: String,
) {
    Row {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            ),
        )
        Spacer(
            modifier = Modifier.width(10.dp),
        )
        Text(
            text = value,
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = 20.sp,
            ),
        )
    }
}
