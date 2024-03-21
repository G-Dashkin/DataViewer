package com.perfomax.dataviewer.data.storage.api

interface FeedsStorage {
    suspend fun add(feedName: String)
    suspend fun getAll(): List<String>
}