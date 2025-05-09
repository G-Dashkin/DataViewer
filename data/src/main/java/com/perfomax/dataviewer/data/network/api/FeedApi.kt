package com.perfomax.dataviewer.data.network.api

import com.perfomax.dataviewer.domain.models.Feed

interface FeedApi {
    fun getData(feedUrl: String): String
    fun updateFeedElements(feed: Feed): Feed
}