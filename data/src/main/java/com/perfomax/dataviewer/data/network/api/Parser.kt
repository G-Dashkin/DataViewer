package com.perfomax.dataviewer.data.network.api

import android.util.Log

class Parser {

    companion object {

        fun parsingToList(feed: String): List<String> {

            val feedElements = ArrayList<String>() // Массив элементов фида
            var oldElement: String
            var newElement: String
            val processedFeed = feed.replace("\\s+".toRegex(), " ").replace("> <", "><")

            processedFeed.split("><").forEach { offerTag ->
                var varTag = offerTag
                if (varTag.first().toString() == "<") varTag = varTag.replace("<", "")
                else if (varTag.last().toString() == ">") varTag = varTag.replace(">", "")

                if (feedElements.isEmpty()) {
                    feedElements.add("<$varTag>")
                } else {
                    oldElement = feedElements.last().split(" ")[0].replace("<","")
                    newElement = varTag.replace("/","")
                    if (oldElement == newElement) feedElements.set(feedElements.size-1, feedElements.last() + "<$varTag>")
                    feedElements.add("<$varTag>")
                }
            }
            return feedElements
        }

    }
}
