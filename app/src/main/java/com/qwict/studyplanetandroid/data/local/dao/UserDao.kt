package com.qwict.studyplanetandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.qwict.studyplanetandroid.data.local.schema.DatabaseUserWithPlanets
import com.qwict.studyplanetandroid.data.local.schema.UserRoomEntity
import kotlinx.coroutines.flow.Flow
/**
 * Data Access Object (DAO) for interacting with the local database regarding [UserRoomEntity].
 * Defines methods to perform CRUD (Create, Read, Update, Delete) operations on user entities.
 */
@Dao
interface UserDao {
    /**
     * Inserts a single user entity into the database. If a conflict arises, the existing entry is replaced.
     *
     * @param user The user entity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserRoomEntity)

    /**
     * Inserts a list of user entities into the database. If a conflict arises, the existing entries are replaced.
     *
     * @param users The list of user entities to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserRoomEntity>)

    /**
     * Updates an existing user entity in the database.
     *
     * @param user The updated user entity.
     */
    @Update
    suspend fun update(user: UserRoomEntity)

    /**
     * Deletes a user entity from the database.
     *
     * @param user The user entity to be deleted.
     */
    @Delete
    suspend fun delete(user: UserRoomEntity)

    /**
     * Retrieves a flow of a user entity by its identifier from the database.
     *
     * @param id The identifier of the user.
     * @return A [Flow] emitting the user entity with the specified identifier.
     */
    @Query("SELECT * FROM users WHERE id = :id")
    fun getFlowUserById(id: Int): Flow<UserRoomEntity>

    /**
     * Retrieves a user entity by its identifier from the database.
     *
     * @param id The identifier of the user.
     * @return The user entity with the specified identifier.
     */
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): UserRoomEntity

    /**
     * Retrieves a user entity by its remote identifier from the database.
     *
     * @param remoteId The remote identifier of the user.
     * @return The user entity with the specified remote identifier.
     */
    @Query("SELECT * FROM users WHERE remoteId = :remoteId")
    suspend fun getUserByRemoteId(remoteId: Int): UserRoomEntity

    /**
     * Retrieves a list of user entities along with associated planet entities from the database.
     *
     * @return A list of [DatabaseUserWithPlanets], each containing a user entity and associated planets.
     */
    @Transaction
    @Query("SELECT * FROM users")
    fun getUsersWithPlanets(): List<DatabaseUserWithPlanets>
}
