package com.perfomax.dataviewer.data.network.api

import java.net.HttpURLConnection
import java.net.URL


class FeedApiImpl: FeedApi {
    override fun getData(feedUrl: String): List<String> {
        val url = URL(feedUrl)
        val connection = url.openConnection() as HttpURLConnection
        val citiFeed = connection.inputStream.bufferedReader().use { it.readText() }
        val arrayFeed = Parser.parsingToList(citiFeed)
        return arrayFeed
    }
}
