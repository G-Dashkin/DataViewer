package com.perfomax.dataviewer.data.storage.api

import com.perfomax.dataviewer.domain.models.Feed

interface FeedsStorage {
    suspend fun add(feedName: String)
    suspend fun remove(feedName:String)
    suspend fun getAllByProject(project: String): List<String>
}