package com.dashkin.dataviewer.feature.nodedetail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashkin.dataviewer.feature.nodedetail.presentation.state.NodeDetailEvent
import com.dashkin.dataviewer.feature.nodedetail.presentation.state.NodeDetailState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NodeDetailViewModel : ViewModel() {

    private val _state = MutableStateFlow(NodeDetailState())
    val state: StateFlow<NodeDetailState> = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<NodeDetailEvent>()
    val sideEffects: SharedFlow<NodeDetailEvent> = _sideEffects.asSharedFlow()

    fun onEvent(event: NodeDetailEvent) {
        when (event) {
            is NodeDetailEvent.LoadNode -> loadNode(event.nodeId, event.fileUri)
            is NodeDetailEvent.CopyValue -> emitEffect(event)
            is NodeDetailEvent.CopyPath -> emitEffect(event)
            is NodeDetailEvent.DismissError -> _state.update { it.copy(error = null) }
        }
    }

    private fun loadNode(nodeId: String, fileUri: String) {
        viewModelScope.launch {
            _state.update { it.copy(nodeId = nodeId, fileUri = fileUri, isLoading = true) }
            // Parser integration will go here
            _state.update { it.copy(isLoading = false) }
        }
    }

    private fun emitEffect(event: NodeDetailEvent) {
        viewModelScope.launch { _sideEffects.emit(event) }
    }
}
