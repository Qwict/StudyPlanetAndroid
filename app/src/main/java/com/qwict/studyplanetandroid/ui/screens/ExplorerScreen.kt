package com.qwict.studyplanetandroid.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
import androidx.compose.ui.* // ktlint-disable no-wildcard-imports
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.api.IApiService
import com.qwict.studyplanetandroid.data.Planet
import com.qwict.studyplanetandroid.helper.RetrofitSingleton
import com.qwict.studyplanetandroid.ui.MainViewModel
import com.qwict.studyplanetandroid.ui.components.AlertDialog
import com.qwict.studyplanetandroid.ui.components.CustomCountDownTimer
import com.qwict.studyplanetandroid.ui.components.MiningPlanetProgressbar
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID

private val validationId = UUID.randomUUID().toString()

@Composable
fun ExplorerScreen(
    onCancelMiningButtonClicked: () -> Unit = {},
    selectedTime: Long,
    planet: Planet,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
) {
    val openAlertDialog = remember { mutableStateOf(false) }
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
                    text = (selectedTime / 1000 / 60).toString() + " Minutes",
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
            CustomCountDownTimer(selectedTime, viewModel)
            MiningPlanetProgressbar(planet, selectedTime, viewModel)

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

fun startMining(selectedTime: Long, planet: Planet, viewModel: MainViewModel) {
    val body = mapOf(
        "type" to "startExploring",
        "validationId" to validationId,
        "planetId" to planet.id,
        "selectedActionTime" to selectedTime,
    )
    Log.i("ExplorerScreen", body.toString())
    Log.i("ExplorerScreen", viewModel.user.token)
    RetrofitSingleton.getApiService().handleAction("bearer ${viewModel.user.token}", body).enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                Log.i("ExplorerScreen", "Started mining")
            } else {
                Log.e("ExplorerScreen", "Failed to start mining")
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            startMining(selectedTime, planet, viewModel)
        }
    })
}

fun stopMining(viewModel: MainViewModel) {
    val body = mapOf(
        "validationId" to validationId,
    )
    RetrofitSingleton.getApiService().handleAction(viewModel.user.token, body).enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                Log.i("ExplorerScreen", "Stopped mining")
            } else {
                Log.e("ExplorerScreen", "Failed to stop mining")
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//            stopMining()
        }
    })
}
