package com.qwict.studyplanetandroid

import kotlinx.coroutines.test.StandardTestDispatcher

class MainViewModelTest {
    private val testScope = StandardTestDispatcher()
    private val fakeStudyPlanetRepository = FakeStudyPlanetRepository()
}
