package com.qwict.studyplanetandroid.data.local

import kotlinx.coroutines.flow.Flow

interface OfflinePlanetsRepository {
    fun getPlanetsByOwnerId(userOwnerId: Int): Flow<List<DatabasePlanet>>
    fun getPlanetById(id: Int): Flow<DatabasePlanet>
    suspend fun insert(planet: DatabasePlanet)
    suspend fun insertAll(planets: List<DatabasePlanet>)
    suspend fun update(planet: DatabasePlanet)
    suspend fun delete(planet: DatabasePlanet)
}

