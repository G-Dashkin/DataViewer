package com.perfomax.dataviewer.domain.usecases.feeds

import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import com.perfomax.dataviewer.domain.usecases.UseCaseWithoutParams
import com.perfomax.dataviewer.domain.utils.getFeedElement
import com.perfomax.dataviewer.domain.utils.getFeedUrl
import javax.inject.Inject

class CountFeedElementsUseCase @Inject constructor(
    private val repository: FeedsRepository
) : UseCaseWithParams<Unit, List<Feed>> {
    override suspend fun execute(feedsList: List<Feed>) {
        repository.countFeedElements(feedsList)
    }
}