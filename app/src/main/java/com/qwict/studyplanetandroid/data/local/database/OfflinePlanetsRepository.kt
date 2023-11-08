package com.qwict.studyplanetandroid.data.local.database

import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import java.util.UUID

interface OfflinePlanetsRepository {
    suspend fun getPlanetsByOwnerId(userOwnerId: Int): List<PlanetRoomEntity>
    suspend fun getPlanetsByUserUuid(userUuid: UUID): List<PlanetRoomEntity>
    suspend fun getPlanetById(id: Int): PlanetRoomEntity
    suspend fun insert(planet: PlanetRoomEntity)
    suspend fun insertAll(planets: List<PlanetRoomEntity>)
    suspend fun update(planet: PlanetRoomEntity)
    suspend fun delete(planet: PlanetRoomEntity)
}
