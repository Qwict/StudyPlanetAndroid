package com.qwict.studyplanetandroid.data.local.database

import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity

interface OfflinePlanetsRepository {
    suspend fun getPlanetsByRemoteId(remoteId: Int): List<PlanetRoomEntity>
    suspend fun getPlanetById(id: Int): PlanetRoomEntity
    suspend fun insert(planet: PlanetRoomEntity)
    suspend fun insertAll(planets: List<PlanetRoomEntity>)
    suspend fun update(planet: PlanetRoomEntity)
    suspend fun delete(planet: PlanetRoomEntity)
}
