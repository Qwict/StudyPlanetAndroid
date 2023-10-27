package com.qwict.studyplanetandroid.data

import kotlinx.coroutines.flow.Flow

interface PlanetsRepository {
    fun getPlanetsByOwnerId(userOwnerId: Int): Flow<List<Planet>>
    fun getPlanetById(id: Int): Flow<Planet>
    suspend fun insert(planet: Planet)
    suspend fun insertAll(planets: List<Planet>)
    suspend fun update(planet: Planet)
    suspend fun delete(planet: Planet)
}

