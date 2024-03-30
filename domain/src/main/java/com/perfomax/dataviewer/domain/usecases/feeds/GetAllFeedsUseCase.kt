package com.perfomax.dataviewer.domain.usecases.feeds

import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import javax.inject.Inject

class GetAllFeedsUseCase @Inject constructor(
    private val repository: FeedsRepository
): UseCaseWithParams<List<Feed>, String> {

    override suspend fun execute(project: String): List<Feed> {
        return repository.getAllFeedsByProject(project = project)
    }
}