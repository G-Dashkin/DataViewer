package com.dashkin.dataviewer.feature.nodedetail.presentation.state

import com.dashkin.dataviewer.core.parser.model.NodeType

data class NodeDetailState(
    val nodeId: String = "",
    val fileUri: String = "",
    val key: String? = null,
    val fullValue: String = "",
    val type: NodeType? = null,
    val jsonPath: String = "",
    val sizeBytes: Long = 0L,
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class NodeDetailEvent {
    data class LoadNode(val nodeId: String, val fileUri: String) : NodeDetailEvent()
    data object CopyValue : NodeDetailEvent()
    data object CopyPath : NodeDetailEvent()
    data object DismissError : NodeDetailEvent()
}
