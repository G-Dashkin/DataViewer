package com.perfomax.dataviewer.domain.usecases

import com.perfomax.dataviewer.domain.repository.ProjectsRepository

class CreateNewProjectUseCase(
    private val repository: ProjectsRepository
):UseCaseWithParams<Unit,String> {
    override suspend fun execute(projectName: String) {
        repository.create(projectName)
    }
}