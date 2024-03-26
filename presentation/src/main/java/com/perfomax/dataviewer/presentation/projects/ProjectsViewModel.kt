package com.perfomax.dataviewer.presentation.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.usecases.projects.CreateNewProjectUseCase
import com.perfomax.dataviewer.domain.usecases.projects.GetAllProjectsUseCase
import com.perfomax.dataviewer.domain.usecases.projects.GetSelectedProjectUseCase
import com.perfomax.dataviewer.domain.usecases.projects.RemoveProjectUseCase
import com.perfomax.dataviewer.domain.usecases.projects.SelectProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val createNewProjectUseCase: CreateNewProjectUseCase,
    private val getAllProjectsUseCase: GetAllProjectsUseCase,
    private val removeProjectUseCase: RemoveProjectUseCase,
    private val selectProjectUseCase: SelectProjectUseCase,
    private val getSelectedProjectUseCase: GetSelectedProjectUseCase
): ViewModel(), ProjectsContract {

    private val _uiState = MutableStateFlow(ProjectsContract.State.initial())
    override val uiState: StateFlow<ProjectsContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<ProjectsContract.Effect?>(null)
    override val effect: StateFlow<ProjectsContract.Effect?> = _effect.asStateFlow()

    init {
        viewModelScope.launch {
            loadProjectsList()
            getSelectedProject()
        }
    }

    override fun intent(event: ProjectsContract.Event) {
        when(event) {
            is ProjectsContract.Event.ProjectNameChangeEvent -> onProjectNameFieldsChange(event.projectName)
            is ProjectsContract.Event.SelectRemovedProjectEvent -> onSelectRemovedProject(event.removedProject)
            is ProjectsContract.Event.SelectProjectEvent -> onSelectProject(event.selectedProject)
            ProjectsContract.Event.CreateNewProjectClickEvent -> onCreateNewProject()
            ProjectsContract.Event.RemoveProjectClickEvent -> onRemoveProject()
            ProjectsContract.Event.OpenDialogCreateEvent -> openDialogCreateNewProject()
            ProjectsContract.Event.CloseDialogCreateEvent -> closeDialogCreateNewProject()
            ProjectsContract.Event.CloseDialogRemoveEvent -> closeDialogRemoveProject()
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun onProjectNameFieldsChange(text: String) {
        _uiState.update { currentState ->
            currentState.copy(
                projectName = text,
                projectNameError = text.isNotEmpty()
            )
        }
    }

    private fun onCreateNewProject() {
        val newProjectName = _uiState.value.projectName
        val newProjectNameValid = newProjectName.isNotEmpty()
        val newProjectNameValid2 = newProjectName.contains("|")
        val newProjectNameValid3 = _uiState.value.projectsList.contains(newProjectName)

        if (newProjectNameValid && !newProjectNameValid2 && !newProjectNameValid3) {
            viewModelScope.launch {
                createNewProjectUseCase.execute(newProjectName)
                closeDialogCreateNewProject()
                onClearUiFieldsState()
                loadProjectsList()
            }
        } else if (newProjectNameValid2) {
            _uiState.update { state ->
                ProjectsContract.State.notCreate()
                state.copy(
                    projectNameError = newProjectName.contains("|"),
                    errorMessage = "Поле проект не должно содержать знаков | "

                )
            }
        } else if(newProjectNameValid3) {
            _uiState.update { state ->
                ProjectsContract.State.notCreate()
                state.copy(
                    projectNameError = _uiState.value.projectsList.contains(newProjectName),
                    errorMessage = "Прокт с названием $newProjectName уже создан"
                )
            }
        } else {
            _uiState.update { state ->
                ProjectsContract.State.notCreate()
                state.copy(
                    projectNameError = newProjectName.isEmpty(),
                    errorMessage = "Поле проект не должно быть пустым"
                )
            }
        }
    }

    private fun onClearUiFieldsState(){
        _uiState.update { currentState ->
            currentState.copy(
                projectName = "",
                errorMessage = ""
            )
        }
    }

    private suspend fun loadProjectsList() {
        _uiState.update { currentState ->
            currentState.copy(
                projectsList = getAllProjectsUseCase.execute()
            )
        }
    }

    private fun onSelectRemovedProject(removeProjectName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                removedProject = removeProjectName
            )
        }
        openDialogRemoveProject()
    }

    private fun onRemoveProject() {
        viewModelScope.launch {
            removeProjectUseCase.execute(_uiState.value.removedProject)
            loadProjectsList()
            closeDialogRemoveProject()
        }
    }

    private fun onSelectProject(selectedProjectName: String) {
        viewModelScope.launch {
            selectProjectUseCase.execute(selectedProjectName)
            getSelectedProject()
        }
    }

    private suspend fun getSelectedProject() {
        _uiState.update { currentState ->
            currentState.copy(
                selectedProject = getSelectedProjectUseCase.execute()
            )
        }
    }

    private fun openDialogCreateNewProject() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogCreateNewProject = true
            )
        }
    }
    private fun closeDialogCreateNewProject() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogCreateNewProject = false
            )
        }
        onClearUiFieldsState()
    }

    private fun openDialogRemoveProject() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogRemoveProject = true,
            )
        }
    }

    private fun closeDialogRemoveProject() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogRemoveProject = false,
            )
        }
    }

}