package com.perfomax.dataviewer.domain.usecases.projects

import com.perfomax.dataviewer.domain.repository.ProjectsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithoutParams
import javax.inject.Inject

class GetAllProjectsUseCase @Inject constructor(
    private val repository: ProjectsRepository
): UseCaseWithoutParams<List<String>> {
    override suspend fun execute(): List<String> {
        return repository.getAll()
    }
}