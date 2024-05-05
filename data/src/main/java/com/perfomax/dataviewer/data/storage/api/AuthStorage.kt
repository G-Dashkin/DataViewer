package com.perfomax.dataviewer.data.storage.api

interface AuthStorage {
    suspend fun add(user: String)
    suspend fun getAll(): List<String>

    suspend fun setAuth(userName: String)
    suspend fun getAuth(): String
}