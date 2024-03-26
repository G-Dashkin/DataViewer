package com.perfomax.dataviewer.data.datastore.api

interface ProjectsDataStore {
    suspend fun getAllProjects(): String
    suspend fun updateProjectsList(projectName: String)
    suspend fun selectProject(projectName: String)
    suspend fun getSelectedProject():String

}