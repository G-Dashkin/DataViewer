package com.perfomax.dataviewer.data.datastore.memory

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.perfomax.dataviewer.data.datastore.api.ProjectsDataStore
import com.perfomax.dataviewer.data.datastore.memory.ProjectsDataStoreImpl.Companion.PROJECTS_DATASTORE
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(PROJECTS_DATASTORE )

class ProjectsDataStoreImpl @Inject constructor(
    private val context: Context
): ProjectsDataStore {

    companion object {
        const val PROJECTS_DATASTORE = "PROJECTS_DATASTORE"
        val PROJECTS_LIST = stringPreferencesKey("PROJECTS_LIST")
        val SELECTED_PROJECT = stringPreferencesKey("SELECTED_PROJECT")
    }

    override suspend fun getAllProjects(): String {
        val preference = context.dataStore.data.first()
        return preference[PROJECTS_LIST]?:""
    }

    override suspend fun updateProjectsList(projectName: String) {
        context.dataStore.edit { preferences ->
            preferences[PROJECTS_LIST] = projectName
        }
    }

    override suspend fun selectProject(projectName: String) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_PROJECT] = projectName
        }
    }

    override suspend fun getSelectedProject(): String {
        val preference = context.dataStore.data.first()
        return preference[SELECTED_PROJECT]?:""
    }
}