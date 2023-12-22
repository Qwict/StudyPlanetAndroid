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

/**
 * The main [Activity] for the StudyPlanet application.
 *
 * The [MainActivity] serves as the entry point for the application and is responsible for setting up the
 * user interface using Jetpack Compose. It also includes lifecycle methods such as [onCreate], [onResume], and [onPause].
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is first created. It sets up the Compose UI and the main content of the application.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle
     * contains the data it most recently supplied in [onSaveInstanceState].
     */
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

    /**
     * Called after [onCreate] or after the activity has been stopped, when it's about to start running again.
     * It is used to validate the user's authentication status when the activity resumes.
     */
    override fun onResume() {
        super.onResume()
        validateUser()
    }

    /**
     * Called as part of the activity lifecycle when an activity is going into the background, but has not (yet) been stopped.
     */
    override fun onPause() {
        super.onPause()
    }

    /**
     * Gets the context of the activity.
     *
     * @return The [Context] associated with this activity.
     */
    fun getContext(): Context {
        return this
    }
}
