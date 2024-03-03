package com.perfomax.dataviewer.presentation.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _authenticated = MutableStateFlow(false)
    val authenticated: StateFlow<Boolean> = _authenticated
}

//data class MainUiState(
//    val screen: Screens,
//)
//
//class MainViewModel : ViewModel() {
//
//    private val _uiState = MutableStateFlow(
//        MainUiState(
//            screen = Screens.HOME,
//        )
//    )
//    val uiState: StateFlow<MainUiState> = _uiState
//
//    fun onHomeClick() {
//        _uiState.update {
//            it.copy(
//                screen = Screens.HOME
//            )
//        }
//    }
//
//    fun onScanningClick() {
//        _uiState.update {
//            it.copy(
//                screen = Screens.SCANNING
//            )
//        }
//    }
//
//    fun onFeedsClick() {
//        _uiState.update {
//            it.copy(
//                screen = Screens.FEEDS
//            )
//        }
//    }
//
//    fun onProjectsClick() {
//        _uiState.update {
//            it.copy(
//                screen = Screens.PROJECTS
//            )
//        }
//    }
//
//}