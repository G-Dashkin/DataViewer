package com.perfomax.dataviewer.domain.usecases.feeds

import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import com.perfomax.dataviewer.domain.usecases.UseCaseWithoutParams
import javax.inject.Inject

class GetAllFeedsUseCase @Inject constructor(
    private val repository: FeedsRepository
): UseCaseWithParams<List<String>, String> {

    override suspend fun execute(project: String): List<String> {
        return repository.getAllByProject(project = project)
    }
}