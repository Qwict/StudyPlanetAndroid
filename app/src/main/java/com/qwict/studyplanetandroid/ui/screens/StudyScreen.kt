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
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.api.Api
import com.qwict.studyplanetandroid.data.OldPlanet
import com.qwict.studyplanetandroid.ui.components.AlertDialog
import com.qwict.studyplanetandroid.ui.components.CustomCountDownTimer
import com.qwict.studyplanetandroid.ui.components.loadProgress
import com.qwict.studyplanetandroid.ui.viewModels.AppViewModelProvider
import com.qwict.studyplanetandroid.ui.viewModels.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID

private val validationId = UUID.randomUUID().toString()

@Composable
fun ExplorerScreen(
    onCancelMiningButtonClicked: () -> Unit = {},
    isDiscovering: Boolean,
    planet: OldPlanet,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    var currentProgress by remember { mutableStateOf(0f) }

    LaunchedEffect(true) {
        scope.launch {
            try {
                if (isDiscovering) {
                    startDiscovering(viewModel.selectedTime, viewModel)
                } else {
                    startExploring(viewModel.selectedTime.toLong(), planet, viewModel)
                }
                loadProgress({ progress ->
                    currentProgress = progress
                }, viewModel.selectedTime.toLong())
                if (isDiscovering) {
                    stopDiscovering(viewModel)
                } else {
                    stopExploring(viewModel)
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
                Image(
                    painter = painterResource(id = planet.imageId ?: R.drawable.earth),
                    contentDescription = planet.name,
                )
                Text(
                    text = (viewModel.selectedTime / 1000 / 60).toString() + " Minutes",
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
            CustomCountDownTimer(viewModel)
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

fun startExploring(selectedTime: Long, planet: OldPlanet, viewModel: MainViewModel) {
    val body = buildJsonObject {
        put("planetId", planet.id.toString())
        put("selectedTime", selectedTime.toString())
    }

    Log.i("ExplorerScreen", body.toString())
    Log.i("ExplorerScreen", viewModel.decodedUser.token)
    Api.service.startExploring(
//        "bearer ${viewModel.user.token}",
        viewModel.decodedUser.token,
        JsonObject(body),
    ).enqueue(object : Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            if (response.isSuccessful) {
                Log.d("ExplorerScreen", "Started mining")
            } else {
                Log.e("ExplorerScreen", "Failed to start mining")
            }
        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            startExploring(selectedTime, planet, viewModel)
        }
    })
}

fun stopExploring(viewModel: MainViewModel) {
    Api.service.finishedExploration(viewModel.decodedUser.token).enqueue(object : Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            if (response.isSuccessful) {
                Log.i("ExplorerScreen", "Stopped mining")
            } else {
                Log.e("ExplorerScreen", "Failed to stop mining")
            }
        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            viewModel.snackBarVisible.value = true
        }
    })
}

fun startDiscovering(selectedTime: Int, viewModel: MainViewModel) {
    Log.i("ExplorerScreen", "Started discovering ${viewModel.decodedUser.token}")
    Api.service.startDiscovery(
        viewModel.decodedUser.token,
        JsonObject(
            buildJsonObject {
                put("selectedTime", selectedTime)
            },
        ),
    ).enqueue(object : Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            if (response.isSuccessful) {
                Log.i("ExplorerScreen", "Started discovering")
            } else {
//                TODO show snackbar
                viewModel.snackBarVisible.value = true
                Log.e("ExplorerScreen", "Failed to start discovering")
            }
        }
        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                TODO show snackbar (possible internet connection not working)
            viewModel.snackBarVisible.value = true
        }
    })
}

fun stopDiscovering(viewModel: MainViewModel) {
    Api.service.finishedDiscovery(viewModel.decodedUser.token).enqueue(object : Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            if (response.isSuccessful) {
                val newPlanet = response.body()?.get("newPlanet") as JsonObject?
                if (newPlanet != null) {
                    val planet = OldPlanet(
                        newPlanet["id"].toString().replace("\"", "").toInt(),
                        newPlanet["name"].toString().replace("\"", ""),
                        newPlanet["imageId"].toString().replace("\"", "").toInt(),
                    )
                    viewModel.decodedUser.discoveredPlanets.add(planet)
                } else {
                    Log.i("ExplorerScreen", "No planet was found, adding experience instead")
//                    viewModel.user.experience += viewModel.selectedTime / 1000 / 60 // for production
                    viewModel.decodedUser.experience.value += viewModel.selectedTime / 1000
                }
                Log.i("ExplorerScreen", "Stopped discovering; it was a success")
                viewModel.resetAction()
            } else {
                Log.e("ExplorerScreen", "Failed to stop discovering")
            }
        }

        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            viewModel.snackBarVisible.value = true
            Log.e("ExplorerScreen", "Failed to stop discovering: there was an a failure")
        }
    })
}
