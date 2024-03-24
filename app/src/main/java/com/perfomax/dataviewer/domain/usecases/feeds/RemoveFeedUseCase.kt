package com.perfomax.dataviewer.domain.usecases.feeds

import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import com.perfomax.dataviewer.domain.usecases.UseCaseWithoutParams
import javax.inject.Inject

class RemoveFeedUseCase @Inject constructor(
    private val repository: FeedsRepository
): UseCaseWithParams<Unit, String> {

    override suspend fun execute(feedName: String) {
        repository.remove(feedName = feedName)
    }
}