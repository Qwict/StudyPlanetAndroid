package com.qwict.studyplanetandroid.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(users: List<User>)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM users WHERE id = :id")
    fun getFlowUserById(id: Int): Flow<User>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): User

    @Transaction
    @Query("SELECT * FROM users")
    fun getUsersWithPlanets(): List<UserWithPlanets>
}
