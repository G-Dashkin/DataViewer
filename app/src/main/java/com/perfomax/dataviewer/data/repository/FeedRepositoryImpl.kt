package com.perfomax.dataviewer.data.repository

import com.perfomax.dataviewer.data.network.api.FeedApi
import com.perfomax.dataviewer.domain.repository.FeedRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FeedRepositoryImpl(
    private val feedApi: FeedApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): FeedRepository {

    override suspend fun get(): List<String> = withContext(dispatcher) {
        feedApi.getData()
    }
}