package com.qwict.studyplanetandroid.data.local

import java.util.UUID

class OfflinePlanetsRepositoryImpl(private val planetDao: PlanetDao) : OfflinePlanetsRepository {

    override suspend fun getPlanetsByOwnerId(userOwnerId: Int): List<DatabasePlanet> {
        return planetDao.getPlanetsByOwnerId(userOwnerId)
    }

    override suspend fun getPlanetsByUserUuid(userUuid: UUID): List<DatabasePlanet> {
        return planetDao.getPlanetsByUserUuid(userUuid)
    }

    override suspend fun getPlanetById(id: Int): DatabasePlanet {
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
