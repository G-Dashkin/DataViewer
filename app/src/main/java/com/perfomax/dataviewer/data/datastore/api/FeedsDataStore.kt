package com.perfomax.dataviewer.data.datastore.api

interface FeedsDataStore {
    suspend fun getAllFeeds(): String
    suspend fun updateFeedsList(feedName: String)
}