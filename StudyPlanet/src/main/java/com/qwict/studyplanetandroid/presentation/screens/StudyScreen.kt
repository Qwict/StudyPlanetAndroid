package com.qwict.studyplanetandroid.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.presentation.components.AlertDialog
import com.qwict.studyplanetandroid.presentation.components.study.CustomCountDownTimer
import com.qwict.studyplanetandroid.presentation.components.study.PlanetDiscoveredDialog
import com.qwict.studyplanetandroid.presentation.viewmodels.StudyViewModel
import com.qwict.studyplanetandroid.presentation.viewmodels.states.StudyState

@Composable
fun ExplorerScreen(
    modifier: Modifier = Modifier,
    navigateBackToMainScreen: () -> Unit = {},
    navigateBackToDiscoveredPlanetsScreen: () -> Unit = {},
    studyViewModel: StudyViewModel = hiltViewModel(),
    state: StudyState = studyViewModel.state,
    isDiscovering: Boolean,
    selectedPlanet: Planet,
    selectedTimeInMinutes: Float,
) {
    BackHandler {
        studyViewModel.openOnBackAlertDialog()
    }

    LaunchedEffect(studyViewModel) {
        studyViewModel.startStudyTimer(selectedTimeInMinutes, isDiscovering)
    }

    Column {
        Text(
            text = selectedPlanet.name,
            modifier =
                Modifier.padding(vertical = 16.dp)
                    .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
        )

        Box(
            propagateMinConstraints = true,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(IntrinsicSize.Min),
        ) {
            Column(
                modifier =
                    modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.tertiaryContainer),
            ) {
                Image(
                    painter = painterResource(selectedPlanet.imageId),
                    contentDescription = selectedPlanet.name,
                    modifier =
                        Modifier
                            .aspectRatio(1f)
                            .fillMaxWidth(),
                    contentScale = ContentScale.Fit,
                )
                Text(
                    text = "Progress",
                    modifier =
                        Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = 16.dp),
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.titleMedium,
                )
                LinearProgressIndicator(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                            .padding(horizontal = 16.dp),
                    progress = state.currentProgress,
                )
                Text(
                    text = "${state.progressPercentage} %",
                    modifier =
                        Modifier
                            .padding(horizontal = 16.dp),
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Column(
                    modifier = Modifier.padding(32.dp),
                ) {
                    CustomCountDownTimer(
                        state.hours,
                        state.minutes,
                        state.seconds,
                    )
                }
            }
        }

        Column(
            modifier =
                modifier
                    .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {
                    studyViewModel.openOnBackAlertDialog()
                },
                modifier =
                    Modifier
                        .padding(16.dp),
            ) {
                Text(text = "Cancel")
            }
        }

        when {
            state.openOnBackAlertDialog -> {
                AlertDialog(
                    onDismissRequest = {
                        studyViewModel.closeBackAlertDialog()
                    },
                    onConfirmation = {
                        navigateBackToMainScreen()
                        studyViewModel.closeBackAlertDialog()
                    },
                    dialogTitle = "Cancel mining operation?",
                    dialogText = "Are you sure you want to stop mining ${selectedPlanet.name}? All progress will be lost.",
                    icon = Icons.Default.Info,
                )
            }
            state.openPlanetDiscoveredDialog -> {
                PlanetDiscoveredDialog(
                    navigateHome = { navigateBackToMainScreen() },
                    navigateToDiscoveredPlanets = { navigateBackToDiscoveredPlanetsScreen() },
                    planet = state.discoveredPlanet,
                    hasDiscoveredPlanet = state.hasDiscoveredPlanet,
                    experience = 0,
//                    state.selectedTime / 1000 / 60,
                )
            }
        }
    }
}
