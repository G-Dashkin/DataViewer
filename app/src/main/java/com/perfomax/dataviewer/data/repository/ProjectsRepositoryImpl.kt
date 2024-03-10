package com.perfomax.dataviewer.data.repository

import com.perfomax.dataviewer.data.storage.ProjectsStorage
import com.perfomax.dataviewer.domain.repository.ProjectsRepository

class ProjectsRepositoryImpl(
    private val projectsStorage: ProjectsStorage
): ProjectsRepository {
    override suspend fun getAll(): List<String> {
        TODO("Not yet implemented")
    }
    override suspend fun create(projectName: String) = projectsStorage.add(projectName)
    override suspend fun remove(id: String)  = projectsStorage.remove(id)
}