package com.perfomax.dataviewer.data.storage.memory

import com.perfomax.dataviewer.data.datastore.api.AuthDataStore
import com.perfomax.dataviewer.data.storage.api.AuthStorage
import com.perfomax.dataviewer.domain.utils.addElement
import com.perfomax.dataviewer.domain.utils.parsToList
import javax.inject.Inject

class AuthStorageImpl @Inject constructor(
    private val datastore: AuthDataStore
): AuthStorage {
    override suspend fun add(user: String) {
        datastore.setUser(user = datastore.getUsers().addElement(user))
    }
    override suspend fun getAll(): List<String> {
        return datastore.getUsers().parsToList()
    }

    override suspend fun setAuth(userName: String) {
        datastore.setAuth(userName = userName)
    }

    override suspend fun getAuth(): String {
        return datastore.getAuth()
    }
}