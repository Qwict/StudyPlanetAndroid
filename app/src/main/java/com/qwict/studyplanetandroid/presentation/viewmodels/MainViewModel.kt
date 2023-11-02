package com.qwict.studyplanetandroid.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.qwict.studyplanetandroid.common.Constants.EMPTY_PLANET
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    var isDiscovering by mutableStateOf(false)
    var selectedTimeInMinutes by mutableStateOf(30f)
    var selectedPlanet by mutableStateOf(EMPTY_PLANET)
}
