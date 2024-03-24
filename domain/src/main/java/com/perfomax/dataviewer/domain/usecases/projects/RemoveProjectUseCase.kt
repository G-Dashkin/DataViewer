package com.perfomax.dataviewer.domain.usecases.projects

import android.util.Log
import com.perfomax.dataviewer.domain.repository.ProjectsRepository
import com.perfomax.dataviewer.domain.usecases.UseCaseWithParams
import javax.inject.Inject

class RemoveProjectUseCase @Inject constructor(
    private val repository: ProjectsRepository
): UseCaseWithParams<Unit, String> {

    override suspend fun execute(projectName: String) {
        repository.remove(projectName = projectName)
    }
}