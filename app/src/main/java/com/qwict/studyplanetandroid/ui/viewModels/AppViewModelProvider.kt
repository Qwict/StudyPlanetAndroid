package com.qwict.studyplanetandroid.ui.viewModels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.qwict.studyplanetandroid.StudyPlanetApplication

object AppViewModelProvider : ViewModelProvider.Factory {
    val Factory = viewModelFactory {
        initializer {
            UserViewModel(
                planetsRepository = studyPlanetApplication().container.planetRepository,
                usersRepository = studyPlanetApplication().container.usersRepository,
            )
        }
        initializer {
            AuthViewModel(
//                planetsRepository = studyPlanetApplication().container.planetRepository,
//                usersRepository = studyPlanetApplication().container.usersRepository,
            )
        }
    }
}

fun CreationExtras.studyPlanetApplication(): StudyPlanetApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as StudyPlanetApplication)
