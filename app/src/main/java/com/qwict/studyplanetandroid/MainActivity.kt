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
import com.qwict.studyplanetandroid.data.Planet
import com.qwict.studyplanetandroid.data.StudyPlanetUiState


import com.qwict.studyplanetandroid.ui.MainScreen
import com.qwict.studyplanetandroid.ui.AuthenticationView
import com.qwict.studyplanetandroid.ui.MainViewModel
import com.qwict.studyplanetandroid.ui.theme.StudyPlanetAndroidTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.setContext(this)
        this.createPlanets(mainViewModel)
        Log.i("MainActivity", "onCreate: ${mainViewModel.discoveredPlanets.size}")
        setContent {
            StudyPlanetAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StudyPlanetApp(mainViewModel);
                }
            }
        }
    }

    private fun createPlanets(mainViewModel: MainViewModel) {
        mainViewModel.discoveredPlanets.add(Planet("Earth", 1.0, 2.0))
        mainViewModel.discoveredPlanets.add(Planet("Mars", 1.0, 2.0))
        mainViewModel.discoveredPlanets.add(Planet("Europe", 1.0, 2.0))
    }
}