package com.qwict.studyplanetandroid.domain.use_case.create_user

import com.qwict.studyplanetandroid.domain.repository.StudyPlanetRepository
import java.util.UUID
import javax.inject.Inject

class CreateLocalUser @Inject constructor(
    private val repo: StudyPlanetRepository,
) {
    private val userUuid = UUID.randomUUID().toString()
}
