package com.qwict.studyplanetandroid.data.local

import kotlinx.coroutines.flow.Flow

class OfflinePlanetsRepositoryImpl(private val planetDao: PlanetDao) : OfflinePlanetsRepository {

    override fun getPlanetsByOwnerId(userOwnerId: Int): Flow<List<DatabasePlanet>> {
        return planetDao.getPlanetsByOwnerId(userOwnerId)
    }

    override fun getPlanetById(id: Int): Flow<DatabasePlanet> {
        return planetDao.getPlanetById(id)
    }

    override suspend fun insert(planet: DatabasePlanet) {
        planetDao.insert(planet)
    }

    override suspend fun insertAll(planets: List<DatabasePlanet>) {
        planetDao.insertAll(planets)
    }

    override suspend fun update(planet: DatabasePlanet) {
        planetDao.update(planet)
    }

    override suspend fun delete(planet: DatabasePlanet) {
        planetDao.delete(planet)
    }
}
