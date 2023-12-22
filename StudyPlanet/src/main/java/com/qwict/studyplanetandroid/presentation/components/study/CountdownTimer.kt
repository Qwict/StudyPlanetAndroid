package com.qwict.studyplanetandroid.presentation.components.study

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun CustomCountDownTimer(
    hours: Int,
    minutes: Int,
    seconds: Int,
    startCountDown: suspend () -> Unit,
) {
    val scope = rememberCoroutineScope() // Create a coroutine scope
    LaunchedEffect(true) {
        scope.launch {
            try {
                startCountDown()
            } catch (e: Exception) {
                Log.i("ExplorerScreen", e.toString())
            }
        }
    }

    Text(
        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
        modifier = Modifier.padding(16.dp),
        text = String.format("%02d:%02d:%02d", hours, minutes, seconds),
    )
}
