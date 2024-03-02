package com.example.dataviewer.presentation.main

import androidx.lifecycle.ViewModel
import com.example.dataviewer.presentation.navigation.Screens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class MainUiState(
    val screen: Screens,
)

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        MainUiState(
            screen = Screens.HOME,
        )
    )
    val uiState: StateFlow<MainUiState> = _uiState

    fun onHomeClick() {
        _uiState.update {
            it.copy(
                screen = Screens.HOME
            )
        }
    }

    fun onScanningClick() {
        _uiState.update {
            it.copy(
                screen = Screens.SCANNING
            )
        }
    }

    fun onFeedsClick() {
        _uiState.update {
            it.copy(
                screen = Screens.FEEDS
            )
        }
    }

    fun onProjectsClick() {
        _uiState.update {
            it.copy(
                screen = Screens.PROJECTS
            )
        }
    }

}