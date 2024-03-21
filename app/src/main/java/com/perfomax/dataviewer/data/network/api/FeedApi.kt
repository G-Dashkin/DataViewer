package com.perfomax.dataviewer.data.network.api

interface FeedApi {
    fun getData(feedUrl: String): List<String>
}