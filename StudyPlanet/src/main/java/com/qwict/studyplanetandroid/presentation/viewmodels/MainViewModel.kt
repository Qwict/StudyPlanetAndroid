package com.qwict.studyplanetandroid.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qwict.studyplanetandroid.common.Constants.EMPTY_USER
import com.qwict.studyplanetandroid.data.StudyPlanetRepository
import com.qwict.studyplanetandroid.presentation.viewmodels.states.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val repository: StudyPlanetRepository,
    ) : ViewModel() {
        var state: MainScreenState by mutableStateOf(MainScreenState.Loading)
            private set

        fun getActiveUser() {
            viewModelScope.launch {
                state = MainScreenState.Loading
                state =
                    try {
                        val user =
                            repository.getActiveUser().stateIn(
                                scope = viewModelScope,
                                started = SharingStarted.WhileSubscribed(5_000),
                                initialValue = EMPTY_USER,
                            )
                        MainScreenState.Success(user)
                    } catch (e: Exception) {
                        MainScreenState.Error(e.message ?: "An unexpected error occurred")
                    }
            }
        }
    }
