package com.qwict.studyplanetandroid.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.UUID

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
    suspend fun getPlanetById(id: Int): DatabasePlanet

    @Query("SELECT * FROM planets WHERE userUuid = :uuid")
    suspend fun getPlanetsByUserUuid(uuid: UUID): List<DatabasePlanet>

    @Query("SELECT * FROM planets WHERE userOwnerId = :userOwnerId")
    suspend fun getPlanetsByOwnerId(userOwnerId: Int): List<DatabasePlanet>
}
