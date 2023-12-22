package com.qwict.studyplanetandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.qwict.studyplanetandroid.data.local.schema.PlanetRoomEntity

/**
 * Data Access Object (DAO) for interacting with the local database regarding [PlanetRoomEntity].
 * Defines methods to perform CRUD (Create, Read, Update, Delete) operations on planet entities.
 */
@Dao
interface PlanetDao {
    /**
     * Inserts a single planet entity into the database. If a conflict arises, the operation is ignored.
     *
     * @param planet The planet entity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(planet: PlanetRoomEntity)

    /**
     * Inserts a list of planet entities into the database. If a conflict arises, the operation is ignored.
     *
     * @param planets The list of planet entities to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(planets: List<PlanetRoomEntity>)

    /**
     * Updates an existing planet entity in the database.
     *
     * @param planet The updated planet entity.
     */
    @Update
    suspend fun update(planet: PlanetRoomEntity)

    /**
     * Deletes a planet entity from the database.
     *
     * @param planet The planet entity to be deleted.
     */
    @Delete
    suspend fun delete(planet: PlanetRoomEntity)

    /**
     * Retrieves a planet entity by its remote identifier from the database.
     *
     * @param remoteId The remote identifier of the planet.
     * @return The planet entity with the specified remote identifier.
     */
    @Query("SELECT * FROM planets WHERE remoteId = :remoteId")
    suspend fun getPlanetByRemoteId(remoteId: Int): PlanetRoomEntity

    /**
     * Retrieves all planet entities associated with a user's remote identifier from the database.
     *
     * @param remoteId The remote identifier of the user.
     * @return A list of planet entities associated with the specified user.
     */
    @Query("SELECT * FROM planets WHERE userOwnerId = :remoteId")
    suspend fun getPlanetsByRemoteId(remoteId: Int): List<PlanetRoomEntity>
}
