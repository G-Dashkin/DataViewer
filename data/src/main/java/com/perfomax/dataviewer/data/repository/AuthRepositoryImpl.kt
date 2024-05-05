package com.perfomax.dataviewer.data.repository

import com.perfomax.dataviewer.data.mappers.toDomainUser
import com.perfomax.dataviewer.data.storage.api.AuthStorage
import com.perfomax.dataviewer.domain.models.User
import com.perfomax.dataviewer.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authStorage: AuthStorage
): AuthRepository {
    override suspend fun create(newUser: String) {
        authStorage.add(user = newUser)
    }

    override suspend fun getAll(): List<User> {
        val usersList = mutableListOf<User>()
        if (authStorage.getAll().size > 1) {
            authStorage.getAll().forEach {
                usersList.add(it.toDomainUser())
            }
        }
        return usersList
    }

    override suspend fun setAuth(userName: String) {
        authStorage.setAuth(userName = userName)
    }

    override suspend fun getAuth(): String {
        return authStorage.getAuth()
    }
}