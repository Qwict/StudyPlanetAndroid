package com.qwict.studyplanetandroid.ui

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.qwict.studyplanetandroid.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun PlanetExplorerScreen(
    onEarthMineButtonClicked: () -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    PlanetName: String = "Earth",
    modifier: Modifier = Modifier
) {
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
                    text = PlanetName,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
//                Image(
//                    painter = painterResource(id = R.drawable.earth),
//                    contentDescription = PlanetName,
//                )
                GifImage()
            }
        }

        Column(
            modifier = modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MiningPlanetProgressbar()

            OutlinedButton(
                onClick = {onCancelButtonClicked()},
                modifier = Modifier
                    .padding(16.dp)

            ) {
                Text(text = "Cancel")
            }
        }
    }



}

@Composable
fun MiningPlanetProgressbar() {
//    var currentProgress = remember { mutableStateOf(0f) }
    var currentProgress = 0f

    LinearProgressIndicator(
        modifier = Modifier.width(300.dp).height(10.dp),
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
        modifier = modifier.fillMaxWidth()
            .height(500.dp)
            .width(500.dp),
    )
}