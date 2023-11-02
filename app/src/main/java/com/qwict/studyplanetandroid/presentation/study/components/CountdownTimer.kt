package com.qwict.studyplanetandroid.presentation.study.components

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.qwict.studyplanetandroid.presentation.viewmodels.StudyViewModel
import kotlinx.coroutines.launch

@Composable
fun CustomCountDownTimer(
    studyViewModel: StudyViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope() // Create a coroutine scope
    LaunchedEffect(true) {
        scope.launch {
            try {
                studyViewModel.countDown()
            } catch (e: Exception) {
                Log.i("ExplorerScreen", e.toString())
            }
        }
    }

    Text(
        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
        modifier = Modifier.padding(16.dp),
        text = String.format("%02d:%02d:%02d", studyViewModel.hours, studyViewModel.minutes, studyViewModel.seconds),
    )
}
