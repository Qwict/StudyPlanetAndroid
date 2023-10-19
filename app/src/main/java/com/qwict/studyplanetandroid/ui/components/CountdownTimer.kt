package com.qwict.studyplanetandroid.ui.components

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.qwict.studyplanetandroid.ui.viewModels.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val secMilSec: Long = 1000
val minMilSec = 60 * secMilSec
val hourMilSec = 60 * minMilSec
val dayMilSec = 24 * hourMilSec

@Composable
fun CustomCountDownTimer(selectedTime: Long, viewModel: MainViewModel) {
    var time by rememberSaveable { mutableStateOf(selectedTime) }
    var hours by remember { mutableStateOf(selectedTime % dayMilSec / hourMilSec) }
    var minutes by remember { mutableStateOf(selectedTime % dayMilSec % hourMilSec / minMilSec) }
    var seconds by remember { mutableStateOf(selectedTime % dayMilSec % hourMilSec % minMilSec / secMilSec) }

    val scope = rememberCoroutineScope() // Create a coroutine scope
    Log.i("ExplorerScreen", "Time selected: $selectedTime")
    LaunchedEffect(true) {
        scope.launch {
            try {
                countDown(
                    time,
                    { updatedTime ->
                        time = updatedTime
                        Log.i("ExplorerScreen", "Time remaining: $updatedTime")
                    },
                    { updatedHours ->
                        hours = updatedHours
                    },
                    { updatedMinutes ->
                        minutes = updatedMinutes
                    },
                    { updatedSeconds ->
                        seconds = updatedSeconds
                    },
                    1000L,
                )
            } catch (e: Exception) {
                Log.i("ExplorerScreen", e.toString())
            }
        }
    }

    Text(
        modifier = Modifier,
        text = String.format("%02d:%02d:%02d", hours, minutes, seconds),
    )
}

suspend fun countDown(
    time: Long,
    updateTime: (Long) -> Unit,
    updateHours: (Long) -> Unit,
    updateMinutes: (Long) -> Unit,
    updateSeconds: (Long) -> Unit,
    delay: Long,
) {
//    for (i in 1..1000) {
//        updateProgress(i.toFloat() / 1000)
//        delay(delay / 100)
//    }

    while (time > 0) {
        Log.i("CountdownTimer", "Time remaining: $time")
        updateHours(time % dayMilSec / hourMilSec)
        updateMinutes(time % dayMilSec % hourMilSec / minMilSec)
        updateSeconds(time % dayMilSec % hourMilSec % minMilSec / secMilSec)
        delay(delay)
        updateTime(time - 1000L)
    }
}
