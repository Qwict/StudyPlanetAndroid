package com.qwict.studyplanetandroid.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.qwict.studyplanetandroid.domain.model.Planet
import com.qwict.studyplanetandroid.presentation.study.components.CustomCountDownTimer
import com.qwict.studyplanetandroid.presentation.viewmodels.StudyViewModel
import com.qwict.studyplanetandroid.presentation.components.AlertDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ExplorerScreen(
    onCancelStudyButtonClicked: () -> Unit = {},
    isDiscovering: Boolean,
    modifier: Modifier = Modifier,
    selectedPlanet: Planet,
    studyViewModel: StudyViewModel = hiltViewModel(),
    selectedTimeInMinutes: Float,
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val studyState by remember { studyViewModel.state }

    var currentProgress by remember { mutableStateOf(0f) }
    LaunchedEffect(true) {
        scope.launch {
            studyViewModel.resetAction()
            Log.i("StudyViewModel", "Started studying, ${studyState.selectedPlanet.name} for $selectedTimeInMinutes minutes; Discovering: $isDiscovering")
            studyState.updatedTime = selectedTimeInMinutes.toInt() * 60 * 1000
            try {
                studyState.selectedTime = selectedTimeInMinutes.toInt() * 60 * 1000
                if (isDiscovering) {
                    studyViewModel.startDiscovering()
                } else {
                    studyViewModel.startExploring()
                }
                loadProgress({ progress ->
                    currentProgress = progress
                }, studyState.selectedTime.toLong())
                if (isDiscovering) {
                    studyViewModel.stopDiscovering()
                } else {
                    studyViewModel.stopExploring()
                }
            } catch (e: Exception) {
                Log.i("ExplorerScreen", e.toString())
            }
        }
    }
    if (isDiscovering) {
    }

    Column {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp,
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(16.dp),
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
            ) {
                Text(
                    text = selectedPlanet.name!!,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
                Image(
                    painter = painterResource(selectedPlanet.imageId),
                    contentDescription = selectedPlanet.name,
                )
                Text(
                    text = (studyState.selectedTime / 1000 / 60).toString() + " Minutes",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomCountDownTimer(
                studyState.hours,
                studyState.minutes,
                studyState.seconds,
            ) { studyViewModel.startCountDown() }
            LinearProgressIndicator(
                modifier = Modifier
                    .width(300.dp)
                    .height(10.dp),
                progress = currentProgress,
            )

            OutlinedButton(
                onClick = { openAlertDialog.value = true },
                modifier = Modifier
                    .padding(16.dp),

            ) {
                Text(text = "Cancel")
            }
        }

        when {
            openAlertDialog.value -> {
                AlertDialog(
                    onDismissRequest = { openAlertDialog.value = false },
                    onConfirmation = {
                        onCancelStudyButtonClicked()
                        openAlertDialog.value = false
                    },
                    dialogTitle = "Cancel mining operation?",
                    dialogText = "Are you sure you want to stop mining ${selectedPlanet.name}? All progress will be lost.",
                    icon = Icons.Default.Info,
                )
            }
        }
    }
}

suspend fun loadProgress(updateProgress: (Float) -> Unit, delay: Long) {
    for (i in 1..1000) {
        updateProgress(i.toFloat() / 1000)
        delay(delay / 1000)
    }
}
