package com.dashkin.dataviewer.feature.treeview.presentation.state

import com.dashkin.dataviewer.core.parser.model.TreeNode

data class TreeViewState(
    val fileUri: String = "",
    val fileName: String = "",
    val nodes: List<TreeNode> = emptyList(),
    val isLoading: Boolean = false,
    val loadingProgress: Float? = null,
    val error: String? = null
)

sealed class TreeViewEvent {
    data class LoadFile(val fileUri: String) : TreeViewEvent()
    data class ExpandNode(val node: TreeNode) : TreeViewEvent()
    data class CollapseNode(val node: TreeNode) : TreeViewEvent()
    data class NavigateToSearch(val fileUri: String) : TreeViewEvent()
    data class NavigateToNodeDetail(val nodeId: String, val fileUri: String) : TreeViewEvent()
    data object DismissError : TreeViewEvent()
}
