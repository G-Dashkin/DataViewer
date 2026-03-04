package com.dashkin.dataviewer.core.parser.model

/**
 * Represents a single node in the parsed file tree.
 *
 * @param id Unique identifier for this node.
 * @param key The key name for object fields; null for array elements.
 * @param type The JSON/XML type of this node.
 * @param depth Nesting depth, starting from 0 for root.
 * @param childCount Number of direct children; null if not yet counted.
 * @param previewValue Short string preview for primitive values.
 * @param filePosition Byte offset in the source file where this node starts.
 * @param isExpanded Whether children are currently loaded and shown.
 * @param children Loaded child nodes; null means not yet loaded, empty means leaf.
 */
data class TreeNode(
    val id: String,
    val key: String?,
    val type: NodeType,
    val depth: Int,
    val childCount: Int?,
    val previewValue: String?,
    val filePosition: Long,
    val isExpanded: Boolean = false,
    val children: List<TreeNode>? = null
) {
    val isLeaf: Boolean get() = type == NodeType.STRING ||
            type == NodeType.NUMBER ||
            type == NodeType.BOOLEAN ||
            type == NodeType.NULL

    val displayKey: String get() = key ?: "[$id]"
}
