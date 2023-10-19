package com.qwict.studyplanetandroid.data

import kotlinx.coroutines.flow.Flow

class OfflinePlanetRepository(private val planetDao: PlanetDao) : PlanetsRepository {

    override fun getPlanets(): Flow<List<PlanetEntity>> {
        return planetDao.getPlanets()
    }

    override fun getPlanetById(id: Int): Flow<PlanetEntity> {
        return planetDao.getPlanetById(id)
    }

    override suspend fun insert(planet: PlanetEntity) {
        planetDao.insert(planet)
    }

    override suspend fun insertAll(planets: List<PlanetEntity>) {
        planetDao.insertAll(planets)
    }

    override suspend fun update(planet: PlanetEntity) {
        planetDao.update(planet)
    }

    override suspend fun delete(planet: PlanetEntity) {
        planetDao.delete(planet)
    }
}
