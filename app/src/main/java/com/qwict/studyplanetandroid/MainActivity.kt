package com.qwict.studyplanetandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.qwict.studyplanetandroid.ui.theme.StudyPlanetAndroidTheme
import com.qwict.studyplanetandroid.ui.viewModels.MainViewModel
import com.qwict.svkandroid.helper.getTokenFromSharedPrefs
import com.qwict.svkandroid.helper.saveEncryptedPreference

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.setContext(this)
        Log.i("MainActivity", "onCreate: ${mainViewModel.user.discoveredPlanets.size}")
        getTokenFromSharedPrefs(mainViewModel, applicationContext)
        setContent {
            StudyPlanetAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    StudyPlanetApp(viewModel = mainViewModel)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getTokenFromSharedPrefs(mainViewModel, applicationContext)
    }

    override fun onPause() {
        super.onPause()
        saveEncryptedPreference("token", mainViewModel.user.token, applicationContext)
    }
}
