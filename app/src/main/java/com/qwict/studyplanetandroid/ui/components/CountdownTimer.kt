package com.qwict.studyplanetandroid.ui.components

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.qwict.studyplanetandroid.ui.viewModels.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun CustomCountDownTimer(viewModel: MainViewModel) {
    val scope = rememberCoroutineScope() // Create a coroutine scope
    LaunchedEffect(true) {
        scope.launch {
            try {
                viewModel.countDown()
            } catch (e: Exception) {
                Log.i("ExplorerScreen", e.toString())
            }
        }
    }

    Text(
        modifier = Modifier,
        text = String.format("%02d:%02d:%02d", viewModel.hours, viewModel.minutes, viewModel.seconds),
    )
}
