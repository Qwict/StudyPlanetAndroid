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
import java.util.UUID

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserRoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserRoomEntity>)

    @Update
    suspend fun update(user: UserRoomEntity)

    @Delete
    suspend fun delete(user: UserRoomEntity)

    @Query("SELECT * FROM users WHERE id = :id")
    fun getFlowUserById(id: Int): Flow<UserRoomEntity>

    @Query("SELECT * FROM users WHERE userUuid = :uuid")
    fun getFlowUserByUuid(uuid: UUID): Flow<UserRoomEntity>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): UserRoomEntity

    @Query("SELECT * FROM users WHERE userUuid = :uuid")
    suspend fun getUserByUuid(uuid: UUID): UserRoomEntity

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserRoomEntity

    @Transaction
    @Query("SELECT * FROM users")
    fun getUsersWithPlanets(): List<DatabaseUserWithPlanets>
}
