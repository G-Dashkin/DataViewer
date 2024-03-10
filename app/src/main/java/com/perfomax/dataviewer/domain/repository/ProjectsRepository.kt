package com.perfomax.dataviewer.domain.repository

interface ProjectsRepository {
    suspend fun getAll(): List<String>
    suspend fun create(projectName: String)
    suspend fun remove(id: String)

}