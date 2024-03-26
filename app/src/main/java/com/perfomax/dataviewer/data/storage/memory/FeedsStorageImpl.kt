package com.perfomax.dataviewer.data.storage.memory

import android.util.Log
import com.perfomax.dataviewer.ui.utils.addElement
import com.perfomax.dataviewer.ui.utils.parsToList
import com.perfomax.dataviewer.ui.utils.parsToListByProject
import com.perfomax.dataviewer.ui.utils.removeFeed
import com.perfomax.dataviewer.ui.utils.removeProject
import com.perfomax.dataviewer.data.datastore.api.FeedsDataStore
import com.perfomax.dataviewer.data.storage.api.FeedsStorage
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
//        return datastore.getAllFeeds().parsToList()

//        datastore.getAllFeeds().parsToListByProject().forEach {
//            Log.d("MyLog", it)
//        }

        return datastore.getAllFeeds().parsToListByProject().filter {
            it.split("projectName:")[1].split(";")[0] == project
        }

//        datastore.getAllFeeds().parsToListByProject()
    }

}