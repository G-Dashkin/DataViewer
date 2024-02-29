package com.example.dataviewer.data.network

import com.example.dataviewer.data.network.api.FeedApi
import com.example.dataviewer.data.network.api.FeedApiNetwork

internal fun provideFeedApi(apiUrl: String): FeedApi {
    return FeedApiNetwork(
        apiUrl = apiUrl
    )
}