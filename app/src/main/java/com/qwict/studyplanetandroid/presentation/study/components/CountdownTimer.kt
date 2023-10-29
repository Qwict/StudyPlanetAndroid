package com.qwict.studyplanetandroid.presentation.study.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CustomCountDownTimer() {
    val scope = rememberCoroutineScope() // Create a coroutine scope
    LaunchedEffect(true) {
        scope.launch {
            try {
//                countDown()
            } catch (e: Exception) {
                Log.i("ExplorerScreen", e.toString())
            }
        }
    }

//    Text(
//        modifier = Modifier,
//        text = String.format("%02d:%02d:%02d", userViewModel.hours, userViewModel.minutes, userViewModel.seconds),
//    )
}
