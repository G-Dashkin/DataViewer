package com.perfomax.dataviewer.data.storage.api

interface FeedsStorage {
    suspend fun add(newFeed: String, selectedProject: String)
    suspend fun remove(removedFeedId: String, selectedProject: String)
    suspend fun update(feedName: String, selectedProject: String)
    suspend fun getAllByProject(project: String): List<String>
}