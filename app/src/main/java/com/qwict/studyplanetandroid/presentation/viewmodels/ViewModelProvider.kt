package com.qwict.studyplanetandroid.presentation.viewmodels
//
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewmodel.CreationExtras
//import androidx.lifecycle.viewmodel.initializer
//import androidx.lifecycle.viewmodel.viewModelFactory
//import com.qwict.studyplanetandroid.StudyPlanetApplication
//
//object ViewModelProvider : ViewModelProvider.Factory {
//    val Factory = viewModelFactory {
//        initializer {
//            AuthViewModel(
//                loginUseCase = studyPlanetApplication(),
//                authenticateUseCase = studyPlanetApplication().authenticateUseCase,
//            )
//        }
//    }
//}
//
//fun CreationExtras.studyPlanetApplication(): StudyPlanetApplication =
//    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as StudyPlanetApplication)