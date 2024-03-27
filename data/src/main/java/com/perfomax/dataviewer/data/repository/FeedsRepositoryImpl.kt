package com.perfomax.dataviewer.data.repository

import android.util.Log
import com.perfomax.dataviewer.data.mappers.toDomainFeed
import com.perfomax.dataviewer.data.network.api.FeedApi
import com.perfomax.dataviewer.data.storage.api.FeedsStorage
import com.perfomax.dataviewer.data.storage.api.ProjectsStorage
import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.domain.repository.FeedsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeedsRepositoryImpl @Inject constructor(
    private val feedApi: FeedApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val feedsStorage: FeedsStorage
): FeedsRepository {

    override suspend fun loadFeed(feedUrl: String): List<String> = withContext(dispatcher) {
        feedApi.getData(feedUrl)
    }

    override suspend fun saveFeed(feedName: String) {
        feedsStorage.add(feedName)
    }

    override suspend fun remove(feedName: String) {
        feedsStorage.remove(feedName)
    }

    override suspend fun getAllByProject(project: String): List<Feed> {
        val feedList = mutableListOf<Feed>()
        feedsStorage.getAllByProject(project).forEach {
            feedList.add(it.toDomainFeed())
        }
        return feedList
    }

    override suspend fun selectFeedElement(feedElement: String) {

    }
}