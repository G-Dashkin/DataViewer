package com.perfomax.dataviewer.domain.usecases.projects

import com.perfomax.dataviewer.domain.repository.ProjectsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithoutParams
import javax.inject.Inject

class GetSelectedProjectUseCase @Inject constructor(
    private val repository: ProjectsRepository
): UseCaseWithoutParams<String> {
    override suspend fun execute(): String {
        return repository.getSelected()
    }
}