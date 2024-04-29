package com.perfomax.dataviewer.data.storage.api

interface FeedsStorage {
    suspend fun add(feedName: String)
    suspend fun remove(feedName:String)
    suspend fun update(feedName:String)
    suspend fun getAllByProject(project: String): List<String>
}