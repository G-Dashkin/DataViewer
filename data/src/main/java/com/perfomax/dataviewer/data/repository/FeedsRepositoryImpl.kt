package com.perfomax.dataviewer.data.repository

import android.util.Log
import com.perfomax.dataviewer.data.mappers.toDomainFeed
import com.perfomax.dataviewer.data.network.api.FeedApi
import com.perfomax.dataviewer.data.network.api.Parser
import com.perfomax.dataviewer.data.storage.api.FeedsStorage
import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.utils.parsToString
import com.perfomax.dataviewer.domain.utils.toShortList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeedsRepositoryImpl @Inject constructor(
    private val feedApi: FeedApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val feedsStorage: FeedsStorage
): FeedsRepository {

    private val feedList: ArrayList<String> = ArrayList()

    private var searchedElement = ""
    private var searchedElementCounter = 0
    private val searchedElementsList: ArrayList<String> = ArrayList()
    private val searchedList: ArrayList<String> = ArrayList()

    override suspend fun loadFeed(feedUrl: String): List<String> = withContext(dispatcher) {
        val stringFeed = feedApi.getData(feedUrl)
        val listFeed = Parser.parsingToList(stringFeed)
        feedList.addAll(listFeed)
        feedList.toShortList()
    }

    override suspend fun countFeedElements(feedList: List<Feed>) = withContext(dispatcher) {
        val updatedFeedList = mutableListOf<Feed>()
        feedList.forEach { feed ->
            val updatedFeedData = feedApi.updateFeedElements(feed)
            val updatedFeed = feed.copy(
                projectName = feed.projectName,
                feedName = feed.feedName,
                feedElement = feed.feedElement,
                feedElementCount = updatedFeedData.feedElementCount,
                feedUrl = feed.feedUrl,
                feedUpdateTime = updatedFeedData.feedUpdateTime,
                feedLoadTime = feed.feedLoadTime
            )
            updatedFeedList.add(updatedFeed)
        }
        feedsStorage.update(updatedFeedList.parsToString())
    }

    override suspend fun saveFeed(feedName: String) {
        feedsStorage.add(feedName)
    }

    override suspend fun remove(feedName: String) {
        feedsStorage.remove(feedName)
    }

    override suspend fun getAllFeedsByProject(project: String): List<Feed> {
        val feedList = mutableListOf<Feed>()
        feedsStorage.getAllByProject(project).forEach {
            feedList.add(it.toDomainFeed())
        }
        return feedList
    }

    override suspend fun searchFeedElement(searchedFeedElement: String): List<String> = withContext(dispatcher) {

        if (searchedElement != searchedFeedElement) {
            searchedElement = searchedFeedElement
            searchedElementsList.clear()
            searchedElementCounter = 0
            searchedElementsList.addAll(feedList.filter { it.contains(searchedFeedElement) })
        }

        val searchedElementIndex = feedList.indexOf(searchedElementsList[searchedElementCounter])
        if (feedList.size < 500) {
            searchedList.clear()
            searchedList.addAll(feedList.subList(0, feedList.size))
        } else if (searchedElementIndex < 250) {
            searchedList.clear()
            searchedList.addAll(feedList.subList(0, searchedElementIndex + 250))
        } else if (feedList.size - searchedElementIndex < 250) {
            searchedList.clear()
            searchedList.addAll(feedList.subList(searchedElementIndex - 250, feedList.size))
        } else {
            searchedList.clear()
            searchedList.addAll(feedList.subList(searchedElementIndex - 250, searchedElementIndex + 250))
        }

        if (searchedElementCounter < searchedElementsList.size-1) {
            searchedElementCounter = searchedElementCounter.inc()
        } else { searchedElementCounter = 0 }

        searchedList
    }

    override suspend fun updateFeedElement(updatedFeed: String) {
        Log.d("MyLog", updatedFeed)

        val updatedFeedList = mutableListOf<Feed>()
//        feedList.forEach { feed ->
//            val updatedFeedData = feedApi.updateFeedElements(feed)
//            val updatedFeed = feed.copy(
//                projectName = feed.projectName,
//                feedName = feed.feedName,
//                feedElement = feed.feedElement,
//                feedElementCount = updatedFeedData.feedElementCount,
//                feedUrl = feed.feedUrl,
//                feedUpdateTime = updatedFeedData.feedUpdateTime,
//                feedLoadTime = feed.feedLoadTime
//            )
//            updatedFeedList.add(updatedFeed)
//        }
        feedsStorage.update(updatedFeed)
    }
}