package com.perfomax.dataviewer.domain.usecases

import com.perfomax.dataviewer.domain.repository.FeedRepository

class GetFeedDataUseCase(
    private val repository: FeedRepository
): UseCaseWithoutParams<List<String>> {
    override suspend fun execute(): List<String> {
        return repository.get()
    }
}