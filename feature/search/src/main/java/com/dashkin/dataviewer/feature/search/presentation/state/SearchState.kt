package com.dashkin.dataviewer.feature.search.presentation.state

import com.dashkin.dataviewer.core.parser.model.TreeNode

data class SearchState(
    val fileUri: String = "",
    val query: String = "",
    val results: List<SearchResult> = emptyList(),
    val isSearching: Boolean = false,
    val searchProgress: Float = 0f,
    val error: String? = null
)

data class SearchResult(
    val node: TreeNode,
    val jsonPath: String,
    val matchedField: MatchedField
)

enum class MatchedField { KEY, VALUE }

sealed class SearchEvent {
    data class LoadFile(val fileUri: String) : SearchEvent()
    data class QueryChanged(val query: String) : SearchEvent()
    data object StartSearch : SearchEvent()
    data object CancelSearch : SearchEvent()
    data class NavigateToNode(val nodeId: String, val fileUri: String) : SearchEvent()
    data object DismissError : SearchEvent()
}
