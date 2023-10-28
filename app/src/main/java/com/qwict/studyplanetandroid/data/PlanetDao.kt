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
    suspend fun insert(planet: Planet)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(planets: List<Planet>)

    @Update
    suspend fun update(planet: Planet)

    @Delete
    suspend fun delete(planet: Planet)

    @Query("SELECT * FROM planets WHERE id = :id")
    fun getPlanetById(id: Int): Flow<Planet>

    @Query("SELECT * FROM planets WHERE userOwnerId = :userOwnerId")
    fun getPlanetsByOwnerId(userOwnerId: Int): Flow<List<Planet>>
}
