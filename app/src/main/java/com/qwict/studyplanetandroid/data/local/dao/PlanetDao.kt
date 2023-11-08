package com.qwict.studyplanetandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity
import java.util.UUID

@Dao
interface PlanetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(planet: PlanetRoomEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(planets: List<PlanetRoomEntity>)

    @Update
    suspend fun update(planet: PlanetRoomEntity)

    @Delete
    suspend fun delete(planet: PlanetRoomEntity)

    @Query("SELECT * FROM planets WHERE id = :id")
    suspend fun getPlanetById(id: Int): PlanetRoomEntity

    @Query("SELECT * FROM planets WHERE userUuid = :uuid")
    suspend fun getPlanetsByUserUuid(uuid: UUID): List<PlanetRoomEntity>

    @Query("SELECT * FROM planets WHERE userOwnerId = :userOwnerId")
    suspend fun getPlanetsByOwnerId(userOwnerId: Int): List<PlanetRoomEntity>
}
