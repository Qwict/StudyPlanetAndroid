package com.qwict.studyplanetandroid.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: DatabaseUser)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(users: List<DatabaseUser>)

    @Update
    suspend fun update(user: DatabaseUser)

    @Delete
    suspend fun delete(user: DatabaseUser)

    @Query("SELECT * FROM users WHERE id = :id")
    fun getFlowUserById(id: Int): Flow<DatabaseUser>

    @Query("SELECT * FROM users WHERE userUuid = :uuid")
    fun getFlowUserByUuid(uuid: UUID): Flow<DatabaseUser>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): DatabaseUser

    @Transaction
    @Query("SELECT * FROM users")
    fun getUsersWithPlanets(): List<DatabaseUserWithPlanets>
}
