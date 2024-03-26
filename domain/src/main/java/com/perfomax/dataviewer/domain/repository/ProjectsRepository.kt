package com.perfomax.dataviewer.domain.repository

interface ProjectsRepository {
    suspend fun create(projectName: String)
    suspend fun getAll(): List<String>
    suspend fun remove(projectName: String)
    suspend fun select(projectName: String)
    suspend fun getSelected(): String
}