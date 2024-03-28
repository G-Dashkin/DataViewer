package com.perfomax.dataviewer.domain.repository

import com.perfomax.dataviewer.domain.models.Feed

interface FeedsRepository {
    suspend fun loadFeed(feedUrl: String): List<String>
    suspend fun countFeedElements(feedElement: String, feedUrl: String): Int
    suspend fun saveFeed(feedName:String)
    suspend fun remove(feedName: String)
    suspend fun selectFeedElement(feedElement: String)
    suspend fun getAllByProject(project: String): List<Feed>
}