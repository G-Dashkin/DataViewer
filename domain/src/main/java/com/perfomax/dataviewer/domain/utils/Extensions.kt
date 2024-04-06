package com.perfomax.dataviewer.domain.utils

import com.perfomax.dataviewer.domain.models.Feed


fun String.parsToList(): List<String> {
    return this.split("|")
}

fun String.parsToListByProject(): List<String> {
    return this.split("|")
}

fun MutableList<Feed>.parsToString(): String {
    return this.joinToString()
               .replace(oldValue = "Feed(", newValue = "")
               .replace(oldValue = "), ", newValue = "|")
               .replace(oldValue = ", ", newValue = ";")
               .replace(oldValue = "=", newValue = ":")
               .replace(oldValue = ")", newValue = "")
}

fun String.addElement(newElement: String): String {
    return if(this.isEmpty()) "$this$newElement" else "$this|$newElement"
}

fun String.removeProject(removedProject: String): String {
    return this.split("|").filter { it != removedProject }.joinToString("|")
}

fun String.removeFeed(feedName: String): String {
    return this.split("|").filter {
        it.split("feedName:")[1].split(";")[0] != feedName
    }.joinToString("|")
}

fun String.updateFeed(feed: String): String {
    var newFeed = ""
    this.split("|").forEach {
        newFeed += if(it.getFeedId() == feed.getFeedId()) "$feed|"
                   else "$it|"
    }
    return newFeed.dropLast(1)
}


fun String.getFeedName(): String {
    return this.split("feedName:")[1].split(";")[0]
}

fun String.getFeedId(): String {
    return this.split("feedId:")[1].split(";")[0]
}
//

fun String.getFeedElementValue(): String {
    return if (this.isNotEmpty()) this.split(":\"")[1].split("\"")[0] else ""
}

fun List<String>.toShortList(): List<String> {

    val feedElements = ArrayList<String>()

    this.forEach { addedElement ->
        if (checkSameElement(feedElements, addedElement) && feedElements.size < 500) {
            feedElements.add(addedElement)
        } else {
            feedElements.set(feedElements.size-1, feedElements.last().replace(">...", ""))
            feedElements.set(feedElements.size-1, feedElements.last().split(" ")[0]+">" + "...")
        }
    }
    return feedElements
}

private fun checkSameElement(arrayFeed: ArrayList<String>, addedElement: String): Boolean {
    var newNewArray = ArrayList<String>()
    val newAddedElement = addedElement.split(" ")[0]

    if (arrayFeed.size >= 11) {
        newNewArray = arrayFeed.slice(arrayFeed.size - 11..arrayFeed.size - 1) as ArrayList
        newNewArray.add(newAddedElement)
        newNewArray.replaceAll {
            it.split(" ")[0]
                .replace("<","")
                .replace(">"," ")
                .split(" ")[0]
        }
    }
    return newNewArray.toSet().size != 1
}

fun List<Feed>.getLastId(): String {
    val listOfIdElements = ArrayList<Int>()
    this.forEach { listOfIdElements.add(it.feedId.toInt()) }
    return if (listOfIdElements.isEmpty()) "1" else listOfIdElements.max().inc().toString()
}

fun String.getFeedElement(): String {
    return this.split("feedElement:")[1].split(";")[0]
}

fun String.getFeedUrl(): String {
    return this.split("feedUrl:")[1].split(";")[0]
}