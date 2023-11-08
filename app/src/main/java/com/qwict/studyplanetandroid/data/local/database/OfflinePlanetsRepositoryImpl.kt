package com.qwict.studyplanetandroid.data.local.database

import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import com.qwict.studyplanetandroid.data.local.dao.PlanetDao
import java.util.UUID

class OfflinePlanetsRepositoryImpl(private val planetDao: PlanetDao) : OfflinePlanetsRepository {

    override suspend fun getPlanetsByOwnerId(userOwnerId: Int): List<PlanetRoomEntity> {
        return planetDao.getPlanetsByOwnerId(userOwnerId)
    }

    override suspend fun getPlanetsByUserUuid(userUuid: UUID): List<PlanetRoomEntity> {
        return planetDao.getPlanetsByUserUuid(userUuid)
    }

    override suspend fun getPlanetById(id: Int): PlanetRoomEntity {
        return planetDao.getPlanetById(id)
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
