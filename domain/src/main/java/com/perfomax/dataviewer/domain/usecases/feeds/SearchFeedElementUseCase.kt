package com.perfomax.dataviewer.domain.usecases.feeds

import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import javax.inject.Inject

class SearchFeedElementUseCase @Inject constructor(
    private val repository: FeedsRepository
): UseCaseWithParams<List<String>, String> {
    override suspend fun execute(searchedFeedElement: String): List<String> {
        return repository.searchFeedElement(searchedFeedElement)
    }
}