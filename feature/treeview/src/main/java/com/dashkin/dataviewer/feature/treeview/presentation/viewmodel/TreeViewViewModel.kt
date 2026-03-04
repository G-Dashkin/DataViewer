package com.dashkin.dataviewer.feature.treeview.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashkin.dataviewer.feature.treeview.presentation.state.TreeViewEvent
import com.dashkin.dataviewer.feature.treeview.presentation.state.TreeViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TreeViewViewModel : ViewModel() {

    private val _state = MutableStateFlow(TreeViewState())
    val state: StateFlow<TreeViewState> = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<TreeViewEvent>()
    val sideEffects: SharedFlow<TreeViewEvent> = _sideEffects.asSharedFlow()

    fun onEvent(event: TreeViewEvent) {
        when (event) {
            is TreeViewEvent.LoadFile -> loadFile(event.fileUri)
            is TreeViewEvent.ExpandNode -> expandNode(event.node)
            is TreeViewEvent.CollapseNode -> collapseNode(event.node)
            is TreeViewEvent.DismissError -> _state.update { it.copy(error = null) }
            is TreeViewEvent.NavigateToSearch,
            is TreeViewEvent.NavigateToNodeDetail -> emitEffect(event)
        }
    }

    private fun loadFile(fileUri: String) {
        viewModelScope.launch {
            _state.update { it.copy(fileUri = fileUri, isLoading = true, error = null) }
            // Parser integration will go here
            _state.update { it.copy(isLoading = false) }
        }
    }

    private fun expandNode(node: com.dashkin.dataviewer.core.parser.model.TreeNode) {
        viewModelScope.launch {
            // Lazy load children from parser will go here
        }
    }

    private fun collapseNode(node: com.dashkin.dataviewer.core.parser.model.TreeNode) {
        _state.update { current ->
            current.copy(
                nodes = current.nodes.map { n ->
                    if (n.id == node.id) n.copy(isExpanded = false, children = null) else n
                }
            )
        }
    }

    private fun emitEffect(event: TreeViewEvent) {
        viewModelScope.launch { _sideEffects.emit(event) }
    }
}
