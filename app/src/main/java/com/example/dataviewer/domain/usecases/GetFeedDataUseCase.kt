package com.example.dataviewer.domain.usecases

import com.example.dataviewer.domain.repository.FeedRepository

class GetFeedDataUseCase(
    private val repository: FeedRepository
): UseCaseWithoutParams<List<String>> {
    override suspend fun execute(): List<String> {
        return repository.get()
    }
}