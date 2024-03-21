package com.perfomax.dataviewer.presentation.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.usecases.projects.CreateNewProjectUseCase
import com.perfomax.dataviewer.domain.usecases.projects.GetAllProjectsUseCase
import com.perfomax.dataviewer.domain.usecases.projects.GetSelectedProjectUseCase
import com.perfomax.dataviewer.domain.usecases.projects.RemoveProjectUseCase
import com.perfomax.dataviewer.domain.usecases.projects.SelectProjectUseCase
import com.perfomax.dataviewer.presentation.projects.ProjectsContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getSelectedProjectUseCase: GetSelectedProjectUseCase
): ViewModel(), MenuContract {

    private val _uiState = MutableStateFlow(MenuContract.State.initial())
    override val uiState: StateFlow<MenuContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<MenuContract.Effect?>(null)
    override val effect: StateFlow<MenuContract.Effect?> = _effect.asStateFlow()

    init {
        getSelectedProject()
    }

    override fun intent(event: MenuContract.Event) {
        when(event){
            is MenuContract.Event.UpdateProjectEvent -> updateProjectName(event.updatedProjectName)
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun updateProjectName(updatedProjectName: String) {
        viewModelScope.launch {
            while (_uiState.value.selectedProject != updatedProjectName) {
                delay(100)
                _uiState.update { currentState ->
                    currentState.copy(
                        selectedProject = getSelectedProjectUseCase.execute()
                    )
                }
            }
        }
    }

    private fun getSelectedProject() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    selectedProject = getSelectedProjectUseCase.execute()
                )
            }
        }
    }

}