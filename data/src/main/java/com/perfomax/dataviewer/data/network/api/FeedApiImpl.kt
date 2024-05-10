package com.perfomax.dataviewer.data.network.api

import android.util.Log
import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.domain.utils.parsToShortList
import java.net.HttpURLConnection
import java.net.URL
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class FeedApiImpl: FeedApi {
    override fun getData(feedUrl: String): String {
        var urlResponse = ""
        var responseCode = ""
        var arrayFeed = ""

        try {
            urlResponse += URL(feedUrl).toString()
        } catch (e:Exception){
            urlResponse += e
        }

        if (!urlResponse.contains("no protocol:")) {
            val url = URL(feedUrl)
            val connection = url.openConnection() as HttpURLConnection
            try {
                responseCode += connection.responseCode.toString()
            } catch (e:Exception){
                responseCode += e
            }

            if (!responseCode.contains("Unable to resolve host")) {
                val processedFeed = connection.inputStream.bufferedReader().use { it.readText() }
                arrayFeed += processedFeed
            } else {
                arrayFeed += "errorURl"
            }
        } else {
            arrayFeed += "errorURl"
        }
        return arrayFeed
    }



    override fun updateFeedElements(feed: Feed): Feed {

        val url = URL(feed.feedUrl)
        val connection = url.openConnection() as HttpURLConnection
        val feedString = connection.inputStream.bufferedReader().use { it.readText() }
        val processedFeed = feedString.replace("\\s+".toRegex(), " ").replace("> <", "><")

        var elementCounter = 0
        var feedUpdateDate = ""
        var feedLoadTime = ""

        if (processedFeed.contains("><")) {
            processedFeed.split("><").forEach {
                if (it.contains(feed.feedElement)){
                    elementCounter = elementCounter.inc()
                }
            }
        } else {
            processedFeed.split("}, {").forEach {
                if (it.contains(feed.feedElement)){
                    elementCounter = elementCounter.inc()
                }
            }
        }


        if (feed.feedUpdateTime.isNotEmpty()) {
            val arrayFeed = Parser.parsingToList(processedFeed).parsToShortList()
            feedUpdateDate+= arrayFeed.find {
                it.contains(feed.feedUpdateTime.split(":\"")[0])
            }
        }

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val instant = Instant.ofEpochMilli(System.currentTimeMillis())
        val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        feedLoadTime = formatter.format(date)

        return Feed(
            feedId = "",
            projectName = "",
            feedName = "",
            feedElement = "",
            feedElementCount = elementCounter,
            oldFeedElementCount = 0,
            isAlertCountFeedDifference = false,
            feedUrl = "",
            feedUpdateTime = feedUpdateDate,
            feedLoadTime = feedLoadTime
        )
    }
}
