package com.qwict.studyplanetandroid.data.local.database

import com.qwict.studyplanetandroid.data.local.dao.PlanetDao
import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import kotlinx.coroutines.flow.Flow

interface OfflinePlanetsRepository {
    fun getPlanetsByOwnerId(ownerId: Int): Flow<List<PlanetRoomEntity>>
    suspend fun removeAllByOwnerId(ownerId: Int)

//    suspend fun getPlanetsByRemoteId(remoteId: Int): List<PlanetRoomEntity>
    suspend fun getPlanetById(id: Int): PlanetRoomEntity
    suspend fun insert(planet: PlanetRoomEntity)
    suspend fun insertAll(planets: List<PlanetRoomEntity>)
    suspend fun update(planet: PlanetRoomEntity)
    suspend fun delete(planet: PlanetRoomEntity)
}

class OfflinePlanetsRepositoryImpl(private val planetDao: PlanetDao) : OfflinePlanetsRepository {
    override fun getPlanetsByOwnerId(ownerId: Int): Flow<List<PlanetRoomEntity>> {
        return planetDao.getPlanetsByOwnerId(ownerId)
    }

    override suspend fun removeAllByOwnerId(ownerId: Int) {
        planetDao.removeAllByOwnerId(ownerId)
    }

//    override suspend fun getPlanetsByRemoteId(remoteId: Int): List<PlanetRoomEntity> {
//        return planetDao.getPlanetsByRemoteId(remoteId)
//    }

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
