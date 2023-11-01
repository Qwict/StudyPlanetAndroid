package com.qwict.studyplanetandroid.data.local

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
    suspend fun insert(planet: DatabasePlanet)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(planets: List<DatabasePlanet>)

    @Update
    suspend fun update(planet: DatabasePlanet)

    @Delete
    suspend fun delete(planet: DatabasePlanet)

    @Query("SELECT * FROM planets WHERE id = :id")
    fun getPlanetById(id: Int): Flow<DatabasePlanet>

    @Query("SELECT * FROM planets WHERE userOwnerId = :userOwnerId")
    fun getPlanetsByOwnerId(userOwnerId: Int): Flow<List<DatabasePlanet>>
}
