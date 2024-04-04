package com.perfomax.dataviewer.data.network.api

import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.domain.utils.toShortList
import java.net.HttpURLConnection
import java.net.URL


class FeedApiImpl: FeedApi {
    override fun getData(feedUrl: String): String {
        var urlResponse = ""
        var responseCode = ""
//        val arrayFeed = mutableListOf<String>()
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
//                arrayFeed.addAll(Parser.parsingToList(processedFeed))
                arrayFeed += processedFeed
            } else {
//                arrayFeed.add("errorURl")
                arrayFeed += "errorURl"
            }
        } else {
//            arrayFeed.add("errorURl")
            arrayFeed += "errorURl"
        }
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
            val arrayFeed = Parser.parsingToList(processedFeed).toShortList()
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
