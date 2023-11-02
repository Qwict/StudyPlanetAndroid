package com.qwict.studyplanetandroid.data.local

import java.util.UUID

interface OfflinePlanetsRepository {
    suspend fun getPlanetsByOwnerId(userOwnerId: Int): List<DatabasePlanet>
    suspend fun getPlanetsByUserUuid(userUuid: UUID): List<DatabasePlanet>
    suspend fun getPlanetById(id: Int): DatabasePlanet
    suspend fun insert(planet: DatabasePlanet)
    suspend fun insertAll(planets: List<DatabasePlanet>)
    suspend fun update(planet: DatabasePlanet)
    suspend fun delete(planet: DatabasePlanet)
}
