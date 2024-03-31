package com.perfomax.dataviewer.data.network.api

import android.util.Log
import com.perfomax.dataviewer.domain.models.Feed
import java.net.HttpURLConnection
import java.net.URL


class FeedApiImpl: FeedApi {
    override fun getData(feedUrl: String): List<String> {
        Log.d("MyLog", "--------------------------1")
        val url = URL(feedUrl)
        Log.d("MyLog", "--------------------------2")
        val connection = url.openConnection() as HttpURLConnection
        Log.d("MyLog", "--------------------------3")
        try {
            Log.d("MyLog", connection.responseCode.toString())
        } catch (e:Exception){
            Log.d("MyLog", "catch: $e")
        }
        Log.d("MyLog", "--------------------------4")
        val processedFeed = connection.inputStream.bufferedReader().use { it.readText() }
        val arrayFeed = Parser.parsingToList(processedFeed)
        return arrayFeed
    }

    override fun updateFeedElements(
        feed: Feed
    ): Feed {

        val url = URL(feed.feedUrl)
        val connection = url.openConnection() as HttpURLConnection
        val feedString = connection.inputStream.bufferedReader().use { it.readText() }
        val processedFeed = feedString.replace("\\s+".toRegex(), " ").replace("> <", "><")
        var elementCounter = 0

        processedFeed.split("><").forEach {
            if (it.contains(feed.feedElement)){
                elementCounter = elementCounter.inc()
            }
        }

        var feedUpdateDate = ""
        if (feed.feedUpdateTime.isNotEmpty()) {
            val arrayFeed = Parser.parsingToList(processedFeed)
            feedUpdateDate+= arrayFeed.find {
                it.contains(feed.feedUpdateTime.split(":\"")[0])
            }
        }

        return Feed(
            projectName = "",
            feedName = "",
            feedElement = "",
            feedElementCount = elementCounter,
            feedUrl = "",
            feedUpdateTime = feedUpdateDate,
            feedLoadTime = ""
        )
    }
}
