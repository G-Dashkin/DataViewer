package com.perfomax.dataviewer.data.storage.memory

import android.util.Log
import com.perfomax.dataviewer.core.utils.addElement
import com.perfomax.dataviewer.core.utils.parsToList
import com.perfomax.dataviewer.core.utils.removeElement
import com.perfomax.dataviewer.data.datastore.api.FeedsDataStore
import com.perfomax.dataviewer.data.storage.api.ProjectsStorage
import com.perfomax.dataviewer.data.datastore.api.ProjectsDataStore
import com.perfomax.dataviewer.data.storage.api.FeedsStorage
import javax.inject.Inject

class FeedsStorageImpl @Inject constructor(
    private val datastore: FeedsDataStore
): FeedsStorage {
    override suspend fun add(feedName: String) {
        datastore.updateFeedsList(feedName)
    }

    override suspend fun getAll(): List<String> {
        return datastore.getAllFeeds().parsToList()
    }

}