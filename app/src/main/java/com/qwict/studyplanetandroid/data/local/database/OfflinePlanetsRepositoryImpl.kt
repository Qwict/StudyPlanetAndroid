package com.qwict.studyplanetandroid.data.local.database

import com.qwict.studyplanetandroid.data.local.dao.PlanetDao
import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity

class OfflinePlanetsRepositoryImpl(private val planetDao: PlanetDao) : OfflinePlanetsRepository {
    override suspend fun getPlanetsByRemoteId(remoteId: Int): List<PlanetRoomEntity> {
        return planetDao.getPlanetsByRemoteId(remoteId)
    }

    override suspend fun getPlanetById(id: Int): PlanetRoomEntity {
        return planetDao.getPlanetByRemoteId(id)
    }

    override suspend fun insert(planet: PlanetRoomEntity) {
        planetDao.insert(planet)
    }

    override suspend fun insertAll(planets: List<PlanetRoomEntity>) {
        planetDao.insertAll(planets)
    }

    override suspend fun update(planet: PlanetRoomEntity) {
        planetDao.update(planet)
    }

    override suspend fun delete(planet: PlanetRoomEntity) {
        planetDao.delete(planet)
    }
}
