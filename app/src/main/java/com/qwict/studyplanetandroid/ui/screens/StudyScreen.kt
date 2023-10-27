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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.qwict.studyplanetandroid.data.Planet
import com.qwict.studyplanetandroid.ui.components.AlertDialog
import com.qwict.studyplanetandroid.ui.components.CustomCountDownTimer
import com.qwict.studyplanetandroid.ui.components.loadProgress
import com.qwict.studyplanetandroid.ui.viewModels.AppViewModelProvider
import com.qwict.studyplanetandroid.ui.viewModels.MainViewModel
import com.qwict.studyplanetandroid.ui.viewModels.UserViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

private val validationId = UUID.randomUUID().toString()

@Composable
fun ExplorerScreen(
    onCancelMiningButtonClicked: () -> Unit = {},
    isDiscovering: Boolean,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory),
    userViewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory),
    planet: Planet,
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    var currentProgress by remember { mutableStateOf(0f) }
    var auth = userViewModel.userIsAuthenticated

    LaunchedEffect(true) {
        scope.launch {
            try {
                if (isDiscovering) {
                    userViewModel.startDiscovering(userViewModel.selectedTime)
                } else {
                    userViewModel.startExploring(userViewModel.selectedTime.toLong())
                }
                loadProgress({ progress ->
                    currentProgress = progress
                }, userViewModel.selectedTime.toLong())
                if (isDiscovering) {
                    userViewModel.stopDiscovering()
                } else {
                    userViewModel.stopExploring()
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
                    text = planet.name!!,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "User is authenticated: $auth",
                )
                Image(
                    painter = painterResource(id = planet.imageId),
                    contentDescription = planet.name,
                )
                Text(
                    text = (userViewModel.selectedTime / 1000 / 60).toString() + " Minutes",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
//                GifImage()
            }
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomCountDownTimer()
//            MiningPlanetProgressbar(viewModel.selectedTime.toLong())
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
                        onCancelMiningButtonClicked()
                        openAlertDialog.value = false
                    },
                    dialogTitle = "Cancel mining operation?",
                    dialogText = "Are you sure you want to stop mining ${planet.name}? All progress will be lost.",
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
