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
import com.qwict.studyplanetandroid.ui.MainViewModel
import com.qwict.studyplanetandroid.ui.theme.StudyPlanetAndroidTheme
import com.qwict.svkandroid.helper.getTokenFromSharedPrefs
import com.qwict.svkandroid.helper.saveEncryptedPreference

class MainActivity : ComponentActivity() {
    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.setContext(this)
        this.createPlanets(mainViewModel)
        Log.i("MainActivity", "onCreate: ${mainViewModel.user.discoveredPlanets.size}")
        setContent {
            StudyPlanetAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    StudyPlanetApp(mainViewModel)
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

    private fun createPlanets(mainViewModel: MainViewModel) {
        Log.i("MainActivity", "createPlanets: creating planets ${R.drawable.earth} ${R.drawable.mars} ${R.drawable.europa}")
        mainViewModel.user.discoveredPlanets.add(Planet(1, "Earth", R.drawable.earth))
        mainViewModel.user.discoveredPlanets.add(Planet(2, "Mars", R.drawable.mars))
        mainViewModel.user.discoveredPlanets.add(Planet(3, "Europe", R.drawable.europa))
    }
}
