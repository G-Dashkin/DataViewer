package com.perfomax.dataviewer.domain.usecases.feeds

import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import com.perfomax.dataviewer.domain.utils.getFeedElement
import com.perfomax.dataviewer.domain.utils.getFeedUrl
import javax.inject.Inject

class CountFeedElementsUseCase @Inject constructor(
    private val repository: FeedsRepository
): UseCaseWithParams<Int, String> {
    override suspend fun execute(feed: String): Int {
        return repository.countFeedElements(
            feedElement = feed.getFeedElement(),
            feedUrl = feed.getFeedUrl()
        )
    }
}