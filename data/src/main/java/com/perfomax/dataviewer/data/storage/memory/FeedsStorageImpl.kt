package com.perfomax.dataviewer.data.storage.memory

import com.perfomax.dataviewer.data.datastore.api.FeedsDataStore
import com.perfomax.dataviewer.data.storage.api.FeedsStorage
import com.perfomax.dataviewer.domain.utils.addElement
import com.perfomax.dataviewer.domain.utils.parsToListByProject
import com.perfomax.dataviewer.domain.utils.removeFeed
import javax.inject.Inject

class FeedsStorageImpl @Inject constructor(
    private val datastore: FeedsDataStore
): FeedsStorage {
    override suspend fun add(feedName: String) {
        datastore.updateFeedsList(feedName = datastore.getAllFeeds().addElement(feedName))
    }

    override suspend fun remove(feedName: String) {
        datastore.updateFeedsList(datastore.getAllFeeds().removeFeed(feedName))
    }

    override suspend fun getAllByProject(project: String): List<String> {
        return if (project.isEmpty()) emptyList()
        else datastore.getAllFeeds().parsToListByProject().filter {
            it.split("projectName:")[1].split(";")[0] == project
        }
    }

}