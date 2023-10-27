package com.qwict.studyplanetandroid.data

import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getUserById(id: Int): Flow<User>
    suspend fun insert(user: User)
    suspend fun insertAll(users: List<User>)
    suspend fun update(user: User)
    suspend fun delete(user: User)
}
