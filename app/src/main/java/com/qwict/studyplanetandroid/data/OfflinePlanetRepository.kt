package com.qwict.studyplanetandroid.data

import kotlinx.coroutines.flow.Flow

class OfflinePlanetRepository(private val planetDao: PlanetDao) : PlanetsRepository {

    override fun getPlanetsByOwnerId(userOwnerId: Int): Flow<List<Planet>> {
        return planetDao.getPlanetsByOwnerId(userOwnerId)
    }

    override fun getPlanetById(id: Int): Flow<Planet> {
        return planetDao.getPlanetById(id)
    }

    override suspend fun insert(planet: Planet) {
        planetDao.insert(planet)
    }

    override suspend fun insertAll(planets: List<Planet>) {
        planetDao.insertAll(planets)
    }

    override suspend fun update(planet: Planet) {
        planetDao.update(planet)
    }

    override suspend fun delete(planet: Planet) {
        planetDao.delete(planet)
    }
}
