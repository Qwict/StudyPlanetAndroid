package com.qwict.studyplanetandroid.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(planet: PlanetEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(planets: List<PlanetEntity>)

    @Update
    suspend fun update(planet: PlanetEntity)

    @Delete
    suspend fun delete(planet: PlanetEntity)

    @Query("SELECT * FROM planets WHERE id = :id")
    fun getPlanetById(id: Int): Flow<PlanetEntity>
    @Query("SELECT * FROM planets")
    fun getPlanets(): Flow<List<PlanetEntity>>
}
