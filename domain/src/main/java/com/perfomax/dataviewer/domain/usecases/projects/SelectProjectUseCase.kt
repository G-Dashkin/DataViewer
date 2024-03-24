package com.perfomax.dataviewer.domain.usecases.projects

import com.perfomax.dataviewer.domain.repository.ProjectsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import javax.inject.Inject

class SelectProjectUseCase @Inject constructor(
    private val repository: ProjectsRepository
): UseCaseWithParams<Unit, String> {

    override suspend fun execute(projectName: String) {
        repository.select(projectName = projectName)
    }
}