package com.perfomax.dataviewer.domain.repository

interface FeedsRepository {
    suspend fun loadFeed(feedUrl: String): List<String>
    suspend fun saveFeed(feedName:String)
    suspend fun selectFeedElement(feedElement: String)
    suspend fun getAll(): List<String>
}