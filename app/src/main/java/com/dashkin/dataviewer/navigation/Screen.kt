package com.dashkin.dataviewer.navigation

import java.net.URLDecoder
import java.net.URLEncoder

sealed class Screen(val route: String) {

    data object FilePicker : Screen("file_picker")

    data object TreeView : Screen("tree_view/{fileUri}") {
        fun createRoute(fileUri: String): String =
            "tree_view/${URLEncoder.encode(fileUri, "UTF-8")}"
    }

    data object Search : Screen("search/{fileUri}") {
        fun createRoute(fileUri: String): String =
            "search/${URLEncoder.encode(fileUri, "UTF-8")}"
    }

    data object NodeDetail : Screen("node_detail/{nodeId}/{fileUri}") {
        fun createRoute(nodeId: String, fileUri: String): String =
            "node_detail/$nodeId/${URLEncoder.encode(fileUri, "UTF-8")}"
    }
}

fun String.decodeNavArg(): String = URLDecoder.decode(this, "UTF-8")
