package com.qwict.studyplanetandroid.ui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.qwict.studyplanetandroid.R
import com.qwict.studyplanetandroid.data.Planet
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun PlanetExplorerScreen(
    onCancelMiningButtonClicked: () -> Unit = {},
    planet: Planet,
    modifier: Modifier = Modifier
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    Column {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(16.dp)
        ) {
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = planet.name!!,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
                Image(
                    painter = painterResource(id = R.drawable.earth),
                    contentDescription = planet.name,
                )
                GifImage()
            }
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RemainingTime(planet.timeToExplore!!)
            MiningPlanetProgressbar()

            OutlinedButton(
                onClick = {openAlertDialog.value = true},
                modifier = Modifier
                    .padding(16.dp)

            ) {
                Text(text = "Cancel")
            }
        }

        when {
            openAlertDialog.value -> {
                AlertDialogExample(
                    onDismissRequest = { openAlertDialog.value = false },
                    onConfirmation = {
                        onCancelMiningButtonClicked()
                        openAlertDialog.value = false
                    },
                    dialogTitle = "Cancel mining operation?",
                    dialogText = "Are you sure you want to stop mining ${planet.name}? All progress will be lost.",
                    icon = Icons.Default.Info
                )
            }
        }

    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}

@Composable
fun RemainingTime(timeToExplore: Double) {
    Text(text = "Remaining Time $timeToExplore")

}

@Composable
fun MiningPlanetProgressbar() {
//    var currentProgress = remember { mutableStateOf(0f) }
    var currentProgress = 0f
    LinearProgressIndicator(
        modifier = Modifier
            .width(300.dp)
            .height(10.dp),
        progress = currentProgress
    )
}

@Composable
fun LinearDeterminateIndicator() {
//    var currentProgress = remember { mutableStateOf(0f) }
    var currentProgress = 0f
    var loading = false
//    var loading = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope() // Create a coroutine scope

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {
            loading = true
            scope.launch {
                loadProgress { progress ->
                    currentProgress = progress
                }
                loading = false // Reset loading when the coroutine finishes
            }
        }, enabled = !loading) {
            Text("Start loading")
        }

        if (loading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                progress = currentProgress
            )
        }
    }
}

/** Iterate the progress value */
suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
//            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
//            } else {
//                add(GifDecoder.Factory())
//            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.turning_earth).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
            .width(500.dp),
    )
}