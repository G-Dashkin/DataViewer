package com.dashkin.dataviewer.feature.filepicker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashkin.dataviewer.core.parser.result.Result
import com.dashkin.dataviewer.feature.filepicker.domain.repository.FileRepository
import com.dashkin.dataviewer.feature.filepicker.presentation.state.FilePickerEvent
import com.dashkin.dataviewer.feature.filepicker.presentation.state.FilePickerState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FilePickerViewModel(
    private val fileRepository: FileRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FilePickerState())
    val state: StateFlow<FilePickerState> = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<FilePickerEvent>()
    val sideEffects: SharedFlow<FilePickerEvent> = _sideEffects.asSharedFlow()

    init {
        loadRecentFiles()
    }

    fun onEvent(event: FilePickerEvent) {
        when (event) {
            is FilePickerEvent.OpenFilePicker -> emitEffect(event)
            is FilePickerEvent.FileSelected -> onFileSelected(event.uri)
            is FilePickerEvent.DismissError -> _state.update { it.copy(error = null) }
            is FilePickerEvent.NavigateToTreeView -> emitEffect(event)
        }
    }

    private fun loadRecentFiles() {
        viewModelScope.launch {
            when (val result = fileRepository.getRecentFiles()) {
                is Result.Success -> _state.update { it.copy(recentFiles = result.data) }
                is Result.Error -> _state.update { it.copy(error = result.exception.message) }
            }
        }
    }

    private fun onFileSelected(uri: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val result = fileRepository.getFileInfo(uri)) {
                is Result.Success -> {
                    val fileInfo = result.data
                    fileRepository.saveRecentFile(fileInfo)
                    _state.update { it.copy(isLoading = false, selectedFile = fileInfo) }
                    emitEffect(FilePickerEvent.NavigateToTreeView(uri))
                }
                is Result.Error -> _state.update {
                    it.copy(isLoading = false, error = result.exception.message)
                }
            }
        }
    }

    private fun emitEffect(event: FilePickerEvent) {
        viewModelScope.launch { _sideEffects.emit(event) }
    }
}
