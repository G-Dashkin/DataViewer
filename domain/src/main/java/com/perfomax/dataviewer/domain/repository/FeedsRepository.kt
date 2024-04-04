package com.perfomax.dataviewer.domain.repository

import com.perfomax.dataviewer.domain.models.Feed

interface FeedsRepository {
    suspend fun loadFeed(feedUrl: String): List<String>
    suspend fun countFeedElements(feedList: List<Feed>)
    suspend fun saveFeed(feedName:String)
    suspend fun remove(feedName: String)
    suspend fun getAllFeedsByProject(project: String): List<Feed>
    suspend fun searchFeedElement(searchedFeedElement: String): List<String>
}