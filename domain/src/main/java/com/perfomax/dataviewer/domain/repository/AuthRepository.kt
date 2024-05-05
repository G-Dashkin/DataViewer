package com.perfomax.dataviewer.domain.repository

import com.perfomax.dataviewer.domain.models.User

interface AuthRepository {
    suspend fun create(newUser: String)
    suspend fun getAll(): List<User>
    suspend fun setAuth(userName: String)
    suspend fun getAuth(): String
}