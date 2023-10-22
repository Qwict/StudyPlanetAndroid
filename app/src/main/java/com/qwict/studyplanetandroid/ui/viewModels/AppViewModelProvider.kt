package com.qwict.studyplanetandroid.ui.viewModels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.qwict.studyplanetandroid.StudyPlanetApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            DataViewModel(
                planetsRepository = studyPlanetApplication().container.planetRepository,
            )
        }
    }
}

fun CreationExtras.studyPlanetApplication(): StudyPlanetApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as StudyPlanetApplication)
