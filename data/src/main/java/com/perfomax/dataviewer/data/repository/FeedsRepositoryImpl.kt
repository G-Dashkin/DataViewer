package com.perfomax.dataviewer.data.repository

import com.perfomax.dataviewer.data.mappers.toDomainFeed
import com.perfomax.dataviewer.data.network.api.FeedApi
import com.perfomax.dataviewer.data.network.api.Parser
import com.perfomax.dataviewer.data.storage.api.FeedsStorage
import com.perfomax.dataviewer.data.storage.api.ProjectsStorage
import com.perfomax.dataviewer.data.storage.api.SettingsStorage
import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.utils.parsToShortList
import com.perfomax.dataviewer.domain.utils.parsToString
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeedsRepositoryImpl @Inject constructor(
    private val feedApi: FeedApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val feedsStorage: FeedsStorage,
    private val settingsStorage: SettingsStorage,
    private val projectsStorage: ProjectsStorage
): FeedsRepository {

    private val feedList: ArrayList<String> = ArrayList()

    private var searchedElement = EMPTY
    private var searchedElementCounter = 0
    private val searchedElementsList: ArrayList<String> = ArrayList()
    private val searchedList: ArrayList<String> = ArrayList()

    override suspend fun loadFeed(feedUrl: String): List<String> = withContext(dispatcher) {
        val stringFeed = feedApi.getData(feedUrl).lines()
                                                 .joinToString("")
                                                 .replace("\\s+".toRegex(), " ")
        val listFeed = Parser.parsingToList(stringFeed)
        feedList.addAll(listFeed)
        feedList.parsToShortList()
    }

    override suspend fun countFeedElements(feedList: List<Feed>) = withContext(dispatcher) {
        val selectedProject = projectsStorage.getSelected()
        val updatedFeedList = mutableListOf<Feed>()
        var countElementsDifferent = 0.0f
        val differentValue = if(settingsStorage.getPercentForAlert().isEmpty()) "0.1"
                             else settingsStorage.getPercentForAlert().split("comparisonPercent:")[1]
        val selectedAlertPercent = differentValue.toFloat()

        var testCounter = 0
        feedList.forEach { feed ->
            testCounter+=1
            val updatedFeedData = feedApi.updateFeedElements(feed)
            if (feed.oldFeedElementCount != 0 || updatedFeedData.feedElementCount != feed.feedElementCount){
                countElementsDifferent = 1-(updatedFeedData.feedElementCount).toFloat()/(feed.feedElementCount).toFloat()
            }

            val updatedFeed = feed.copy(
                projectName = feed.projectName,
                feedName = feed.feedName,
                feedElement = feed.feedElement,
                feedElementCount = updatedFeedData.feedElementCount,
                oldFeedElementCount = feed.feedElementCount,
                isAlertCountFeedDifference = countElementsDifferent > selectedAlertPercent,
                feedUrl = feed.feedUrl,
                feedUpdateTime = updatedFeedData.feedUpdateTime,
                feedLoadTime = updatedFeedData.feedLoadTime
            )
            updatedFeedList.add(updatedFeed)
        }
        feedsStorage.update(feedName = updatedFeedList.parsToString(), selectedProject = selectedProject)
    }

    override suspend fun saveFeed(newFeed: String) {
        val selectedProject = projectsStorage.getSelected()
        feedsStorage.add(newFeed = newFeed, selectedProject = selectedProject)
    }

    override suspend fun remove(removedFeed: Feed) {
        feedsStorage.remove(removedFeedId = removedFeed.feedId, selectedProject = removedFeed.projectName)
    }

    override suspend fun getAllFeedsByProject(project: String): List<Feed> {
        val feedList = mutableListOf<Feed>()
        feedsStorage.getAllByProject(project).forEach {
            feedList.add(it.toDomainFeed())
        }
        return feedList
    }

    override suspend fun searchFeedElement(searchedFeedElement: String): List<String> = withContext(dispatcher) {
        var searchedElementIndex = 0
        if (searchedElement != searchedFeedElement) {
            searchedElement = searchedFeedElement
            searchedElementsList.clear()
            searchedElementCounter = 0
            searchedElementsList.addAll(feedList.filter { it.contains(searchedFeedElement) })
        }
        try {
            searchedElementIndex = feedList.indexOf(searchedElementsList[searchedElementCounter])
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
        } catch (e:Exception){ }
        searchedList
    }

    override suspend fun updateFeedElement(updatedFeed: String) {
        val selectedProject = projectsStorage.getSelected()
        feedsStorage.update(feedName = updatedFeed, selectedProject = selectedProject)
    }
}