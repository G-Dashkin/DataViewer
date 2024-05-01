package com.perfomax.dataviewer.domain.models

data class Feed(
    val feedId: String,
    val projectName: String,
    val feedName: String,
    val feedElement: String,
    val feedElementCount: Int,
    val oldFeedElementCount: Int,
    val isAlertCountFeedDifference: Boolean,
    val feedUrl: String,
    val feedUpdateTime: String,
    val feedLoadTime: String
)