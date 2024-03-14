package com.perfomax.dataviewer.presentation.projects

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.repository.DatastoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel
@Inject constructor(
    private val datastoreRepository: DatastoreRepository
): ViewModel(), ProjectsContract {

    private val _uiState = MutableStateFlow(ProjectsContract.State.initial())
    override val uiState: StateFlow<ProjectsContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<ProjectsContract.Effect?>(null)
    override val effect: StateFlow<ProjectsContract.Effect?> = _effect.asStateFlow()

    override fun intent(event: ProjectsContract.Event) {
        when(event) {
            is ProjectsContract.Event.ProjectNameChangeEvent -> {
                onProjectNameFieldsChange(event.projectName)
            }
            is ProjectsContract.Event.GetProjectClickEvent -> {
                getProjectName()
            }
            is ProjectsContract.Event.ClearProjectNameEvent -> {
                onClearUiState()
            }
            ProjectsContract.Event.CreateNewProjectClickEvent -> {
                onCreateNewProject()
            }
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun onProjectNameFieldsChange(text: String) {
        _uiState.update { currentState ->
            currentState.copy(
                projectName = text,
                projectNameError = text.isNotBlank()
            )
        }
    }

    private fun onClearUiState(){
        _uiState.update { currentState ->
            currentState.copy(
                projectName = ""
            )
        }
    }

    private fun onCreateNewProject() {
        val newProjectName = _uiState.value.projectName
        viewModelScope.launch {
            datastoreRepository.putString("PROJECT_NAME", newProjectName)
        }
    }

    private fun getProjectName() {
        viewModelScope.launch {
            val projectNameInDatastore = datastoreRepository.getString("PROJECT_NAME")!!
            Log.d("MyLog", "ProjectName из DataBase: $projectNameInDatastore")
        }
    }

//    fun getUserName():String = runBlocking {
//        datastoreRepository.getString("PROJECT_NAME")!!
//    }

    fun clearPreferences(key:String) {
        viewModelScope.launch {
            datastoreRepository.removePreferences(key)
        }
    }



}