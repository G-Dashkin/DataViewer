package com.perfomax.dataviewer.presentation.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.usecases.auth.GetAuthUseCase
import com.perfomax.dataviewer.domain.usecases.projects.GetSelectedProjectUseCase
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
    private val getSelectedProjectUseCase: GetSelectedProjectUseCase,
    private val getAuthUseCase: GetAuthUseCase
): ViewModel(), MenuContract {

    private val _uiState = MutableStateFlow(MenuContract.State.initial())
    override val uiState: StateFlow<MenuContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<MenuContract.Effect?>(null)
    override val effect: StateFlow<MenuContract.Effect?> = _effect.asStateFlow()

    init {
        getSelectedProject()
        authUser()
    }

    override fun intent(event: MenuContract.Event) {
        when(event){
            MenuContract.Event.UpdateProjectEvent -> updateProject()
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun updateProject() {
        viewModelScope.launch {
            delay(100)
            getSelectedProject()
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

    private fun authUser(){
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    authUser = getAuthUseCase.execute()
                )
            }
        }
    }

}