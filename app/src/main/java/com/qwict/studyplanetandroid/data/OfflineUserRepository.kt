package com.qwict.studyplanetandroid.data

import kotlinx.coroutines.flow.Flow

class OfflineUserRepository(private val userDao: UserDao) : UsersRepository {
    override fun getUserById(id: Int): Flow<User> {
        return userDao.getUserById(id)
    }

    override suspend fun insert(user: User) {
        userDao.insert(user)
    }

    override suspend fun insertAll(users: List<User>) {
        userDao.insertAll(users)
    }

    override suspend fun update(user: User) {
        userDao.update(user)
    }

    override suspend fun delete(user: User) {
        userDao.delete(user)
    }
}
