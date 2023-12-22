package com.qwict.studyplanetandroid.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.presentation.components.LevelExperienceBar
import com.qwict.studyplanetandroid.presentation.viewmodels.states.UserState

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onStartExploringButtonClicked: () -> Unit = {},
    onDiscoverPlanetsButtonClicked: () -> Unit = {},
    userState: UserState,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //            UserPicture(
        //                url = viewModel.user.picture,
        //                description = viewModel.user.name,
        //            )
        UserInfoRow(
            label = stringResource(R.string.email_label),
            value = userState.user.email,
        )
        UserInfoRow(
            label = "username",
            value = userState.user.name,
        )
        LevelExperienceBar(
            level = userState.currentLevel,
            experience = userState.user.experience,
            experienceForNextLevel = userState.experienceForNextLevel,
            experienceProgress = userState.experienceProgress,
        )

        Row() {
//            VideoPlayer()
        }

        Column {
            OutlinedButton(onClick = { onDiscoverPlanetsButtonClicked() }) {
                Text(text = "Discover Planets")
            }
            OutlinedButton(onClick = { onStartExploringButtonClicked() }) {
                Text(text = "Start Exploring")
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
