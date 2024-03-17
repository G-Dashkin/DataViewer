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
            ProjectsContract.Event.ClearProjectNameFieldEvent -> onClearUiFieldsState()
            ProjectsContract.Event.RemoveProjectClickEvent -> onRemoveProject()
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

    private fun onCreateNewProject() {
        val newProjectName = _uiState.value.projectName
        viewModelScope.launch {
            createNewProjectUseCase.execute(newProjectName)
            loadProjectsList()
        }
    }

    private fun onClearUiFieldsState(){
        _uiState.update { currentState ->
            currentState.copy(
                projectName = ""
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
    }

    private fun onRemoveProject() {
        viewModelScope.launch {
            removeProjectUseCase.execute(_uiState.value.removedProject)
            loadProjectsList()
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

}

//class ProjectsViewModelFactory @Inject constructor(
//    private val createNewProjectUseCase: CreateNewProjectUseCase,
//    private val getAllProjectsUseCase: GetAllProjectsUseCase,
//    removeProjectUseCase: RemoveProjectUseCase
////    private val context: Context,
////    private val getAllStudentsUseCase: GetAllStudentsUseCase,
////    private val filterByNameUseCase: FilterByNameUseCase
//):  ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(
//        modelClass: Class<T>,
//        extras: CreationExtras,
//    ): T {
//        return ProjectsViewModel(
//            cre
////            context = context,
////            getAllStudentsUseCase = getAllStudentsUseCase,
////            filterByNameUseCase = filterByNameUseCase
//        ) as T
//    }
//}