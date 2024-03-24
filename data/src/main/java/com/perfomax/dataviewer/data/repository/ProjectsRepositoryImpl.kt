package com.perfomax.dataviewer.data.repository

import android.util.Log
import com.perfomax.dataviewer.data.storage.api.ProjectsStorage
import com.perfomax.dataviewer.domain.repository.ProjectsRepository
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor(
    private val projectsStorage: ProjectsStorage
): ProjectsRepository {
    override suspend fun create(projectName: String) {
        return projectsStorage.add(projectName = projectName)
    }

    override suspend fun getAll(): List<String> {
        return projectsStorage.getAll()
    }

    override suspend fun remove(projectName: String) {
        projectsStorage.remove(projectName)
    }

    override suspend fun select(projectName: String) {
        projectsStorage.select(projectName)
    }

    override suspend fun getSelected(): String {
        return projectsStorage.getSelected()
    }
}