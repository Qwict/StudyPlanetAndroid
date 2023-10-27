package com.qwict.studyplanetandroid

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.qwict.studyplanetandroid.ui.states.StudyPlanetUiState
import com.qwict.studyplanetandroid.ui.theme.StudyPlanetAndroidTheme
import com.qwict.studyplanetandroid.ui.viewModels.UserViewModel
import com.qwict.studyplanetandroid.ui.viewModels.AppViewModelProvider
import com.qwict.studyplanetandroid.ui.viewModels.MainViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {
    val mainViewModel: MainViewModel by viewModels {
        AppViewModelProvider.Factory
    }
    private val userViewModel: UserViewModel by viewModels {
        AppViewModelProvider.Factory
    }

    private var _uiState = MutableStateFlow(
        StudyPlanetUiState(),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mainViewModel.setContext(this)
        userViewModel.authenticationCheckWithToken()
        setContent {
            StudyPlanetAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    StudyPlanetApp()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.authenticationCheckWithToken()
    }

    override fun onPause() {
        super.onPause()
    }

    fun getContext(): Context {
        return this
    }
}
