package com.perfomax.dataviewer.presentation.scanning

import androidx.lifecycle.ViewModel
import com.perfomax.dataviewer.presentation.projects.ProjectsContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScanningViewModel(

): ViewModel(), ScanningContract {

    private val _uiState = MutableStateFlow(ScanningContract.State.initial())
    override val uiState: StateFlow<ScanningContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<ScanningContract.Effect?>(null)
    override val effect: StateFlow<ScanningContract.Effect?> = _effect.asStateFlow()

    override fun intent(event: ScanningContract.Event) {
        when(event) {
            ScanningContract.Event.SomeEvent -> { }
        }
    }

    override fun consume() {
        _effect.update { null }
    }
}