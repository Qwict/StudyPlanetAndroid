package com.qwict.studyplanetandroid.ui.components

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MiningPlanetProgressbar(countDownTime: Long) {
    var currentProgress by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope() // Create a coroutine scope
    Log.i("ExplorerScreen", "Time selected: $countDownTime")
    LaunchedEffect(true) {
        scope.launch {
            try {
                loadProgress({ progress ->
                    currentProgress = progress
                }, countDownTime)
            } catch (e: Exception) {
                Log.i("ExplorerScreen", e.toString())
            }
        }
    }

    LinearProgressIndicator(
        modifier = Modifier
            .width(300.dp)
            .height(10.dp),
        progress = currentProgress,
    )
}

suspend fun loadProgress(updateProgress: (Float) -> Unit, delay: Long) {
    for (i in 1..1000) {
        updateProgress(i.toFloat() / 1000)
        delay(delay / 1000)
    }
}
