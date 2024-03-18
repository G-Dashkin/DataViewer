package com.perfomax.dataviewer.data.storage.memory

import android.util.Log
import com.perfomax.dataviewer.core.utils.addElement
import com.perfomax.dataviewer.core.utils.parsToList
import com.perfomax.dataviewer.core.utils.removeElement
import com.perfomax.dataviewer.data.storage.api.ProjectsStorage
import com.perfomax.dataviewer.data.datastore.api.ProjectsDataStore
import javax.inject.Inject

class ProjectsStorageImpl @Inject constructor(
    private val datastore: ProjectsDataStore
): ProjectsStorage {

    override suspend fun add(projectName: String) {
        datastore.updateProjectsList(projectName = datastore.getAllProjects().addElement(projectName))
    }

    override suspend fun getAll(): List<String> {
        return datastore.getAllProjects().parsToList()
    }

    override suspend fun remove(projectName: String) {
        datastore.updateProjectsList(datastore.getAllProjects().removeElement(projectName))
    }

    override suspend fun select(projectName: String) {
        datastore.selectProject(projectName)
    }

    override suspend fun getSelected(): String {
        return datastore.getSelectedProject()
    }
}