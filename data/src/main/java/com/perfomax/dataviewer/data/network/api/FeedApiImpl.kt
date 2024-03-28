package com.perfomax.dataviewer.data.network.api

import android.util.Log
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

    override fun countFeedElements(feedElement: String, feedUrl: String): Int {
        val url = URL(feedUrl)
        val connection = url.openConnection() as HttpURLConnection
        val feedString = connection.inputStream.bufferedReader().use { it.readText() }
        val processedFeed = feedString.replace("\\s+".toRegex(), " ").replace("> <", "><")
        var elementCounter = 0

        processedFeed.split("><").forEach {
            if (it.contains(feedElement)){
                elementCounter = elementCounter.inc()
            }
        }
        return elementCounter
    }
}
