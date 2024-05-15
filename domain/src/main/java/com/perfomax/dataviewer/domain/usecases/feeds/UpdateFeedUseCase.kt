package com.perfomax.dataviewer.domain.usecases.feeds

import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import javax.inject.Inject

class UpdateFeedUseCase @Inject constructor(
    private val repository: FeedsRepository
) : UseCaseWithParams<Unit, String> {
    override suspend fun execute(feedsList: String) {
        repository.updateFeedElement(feedsList)
    }
}