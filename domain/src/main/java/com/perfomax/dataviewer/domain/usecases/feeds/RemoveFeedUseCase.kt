package com.perfomax.dataviewer.domain.usecases.feeds

import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import javax.inject.Inject

class RemoveFeedUseCase @Inject constructor(
    private val repository: FeedsRepository
): UseCaseWithParams<Unit, Feed> {

    override suspend fun execute(removedFeed: Feed) {
        repository.remove(removedFeed = removedFeed)
    }
}