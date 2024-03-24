package com.perfomax.dataviewer.domain.usecases.feeds

import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import javax.inject.Inject

class LoadFeedUseCase @Inject constructor(
    private val repository: FeedsRepository
): UseCaseWithParams<List<String>, String> {
    override suspend fun execute(feedUrl: String): List<String> {
        return repository.loadFeed(feedUrl)
    }
}