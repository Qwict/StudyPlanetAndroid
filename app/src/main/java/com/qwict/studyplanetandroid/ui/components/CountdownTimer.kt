package com.qwict.studyplanetandroid.ui.components

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.qwict.studyplanetandroid.ui.viewModels.UserViewModel
import com.qwict.studyplanetandroid.ui.viewModels.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun CustomCountDownTimer(
    userViewModel: UserViewModel = viewModel(factory = UserViewModel.factory),
) {
    val scope = rememberCoroutineScope() // Create a coroutine scope
    LaunchedEffect(true) {
        scope.launch {
            try {
                userViewModel.countDown()
            } catch (e: Exception) {
                Log.i("ExplorerScreen", e.toString())
            }
        }
    }

    Text(
        modifier = Modifier,
        text = String.format("%02d:%02d:%02d", userViewModel.hours, userViewModel.minutes, userViewModel.seconds),
    )
}
