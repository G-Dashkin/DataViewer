package com.dashkin.dataviewer.feature.filepicker.presentation.state

import com.dashkin.dataviewer.feature.filepicker.domain.model.FileInfo

data class FilePickerState(
    val recentFiles: List<FileInfo> = emptyList(),
    val selectedFile: FileInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class FilePickerEvent {
    data object OpenFilePicker : FilePickerEvent()
    data class FileSelected(val uri: String) : FilePickerEvent()
    data class NavigateToTreeView(val fileUri: String) : FilePickerEvent()
    data object DismissError : FilePickerEvent()
}
