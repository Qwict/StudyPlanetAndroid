package com.qwict.studyplanetandroid

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.qwict.studyplanetandroid.common.AuthenticationSingleton.validateUser
import com.qwict.studyplanetandroid.presentation.theme.StudyPlanetAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        validateUser()
    }

    override fun onPause() {
        super.onPause()
    }

    fun getContext(): Context {
        return this
    }
}
