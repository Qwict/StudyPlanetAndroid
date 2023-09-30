package com.qwict.studyplanetandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier


import com.qwict.studyplanetandroid.ui.MainScreen
import com.qwict.studyplanetandroid.ui.AuthenticationView
import com.qwict.studyplanetandroid.ui.MainViewModel
import com.qwict.studyplanetandroid.ui.theme.StudyPlanetAndroidTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.setContext(this)
        setContent {
            StudyPlanetAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StudyPlanetApp(mainViewModel);
//                    AuthenticationView(mainViewModel)

                }
            }
        }
    }
}