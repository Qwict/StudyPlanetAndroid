package com.qwict.studyplanetandroid.data.local

import kotlinx.coroutines.flow.Flow

class OfflineUsersRepositoryImpl(private val userDao: UserDao) : OfflineUsersRepository {
    override fun getUserById(id: Int): Flow<DatabaseUser> {
        return userDao.getFlowUserById(id)
    }

    override suspend fun insert(user: DatabaseUser) {
        userDao.insert(user)
    }

    override suspend fun insertAll(users: List<DatabaseUser>) {
        userDao.insertAll(users)
    }

    override suspend fun update(user: DatabaseUser) {
        userDao.update(user)
    }

    override suspend fun delete(user: DatabaseUser) {
        userDao.delete(user)
    }
}

