package com.qwict.studyplanetandroid.data

import kotlinx.coroutines.flow.Flow

interface PlanetsRepository {
    fun getPlanets(): Flow<List<PlanetEntity>>
    fun getPlanetById(id: Int): Flow<PlanetEntity>
    suspend fun insert(planet: PlanetEntity)
    suspend fun insertAll(planets: List<PlanetEntity>)
    suspend fun update(planet: PlanetEntity)
    suspend fun delete(planet: PlanetEntity)
}

