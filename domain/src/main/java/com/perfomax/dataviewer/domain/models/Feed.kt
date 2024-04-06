package com.perfomax.dataviewer.domain.models

data class Feed(
    val feedId: String,
    val projectName: String,
    val feedName: String,
    val feedElement: String,
    val feedElementCount: Int,
    val feedUrl: String,
    val feedUpdateTime: String,
    val feedLoadTime: String
)