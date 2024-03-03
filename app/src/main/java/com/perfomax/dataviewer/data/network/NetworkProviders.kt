package com.perfomax.dataviewer.data.network

import com.perfomax.dataviewer.data.network.api.FeedApi
import com.perfomax.dataviewer.data.network.api.FeedApiNetwork

internal fun provideFeedApi(apiUrl: String): FeedApi {
    return FeedApiNetwork(
        apiUrl = apiUrl
    )
}