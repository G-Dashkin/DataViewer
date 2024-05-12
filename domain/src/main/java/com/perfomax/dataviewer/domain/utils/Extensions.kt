package com.perfomax.dataviewer.domain.utils

import com.perfomax.dataviewer.domain.models.Feed

// Parsing Extensions-------------------------------------------------------------------------------
fun String.parsToList(): List<String> {
    return this.split("|")
}

fun String.parsToListByProject(): List<String> {
    return this.split("|")
}

fun String.parsAlertPercent(): Float {
    return ("0." + this.split(" ")[1].split("0%")[0]).toFloat()
}

fun MutableList<Feed>.parsToString(): String {
    return this.joinToString()
               .replace(oldValue = "Feed(", newValue = "")
               .replace(oldValue = "), ", newValue = "|")
               .replace(oldValue = ", ", newValue = ";")
               .replace(oldValue = "=", newValue = ":")
               .replace(oldValue = ")", newValue = "")
}

fun List<String>.parsToShortList(): List<String> {

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
// GetValue Extensions------------------------------------------------------------------------------
fun String.getAlertPercent(): Float {
    return this.split("comparisonPercent:")[1].toFloat()
}

fun String.getFeedId(): String {
    return this.split("feedId:")[1].split(";")[0]
}
fun String.getProjectName(): String {
    return this.split("projectName:")[1].split(";")[0]
}


fun String.getFeedElementValue(): String {
    return if (this.isNotEmpty()) this.split(":\"")[1].split("\"")[0] else ""
}

fun List<Feed>.getLastId(): String {
    val listOfIdElements = ArrayList<Int>()
    this.forEach { listOfIdElements.add(it.feedId.toInt()) }
    return if (listOfIdElements.isEmpty()) "1" else listOfIdElements.max().inc().toString()
}
// Modifier Extensions------------------------------------------------------------------------------
fun String.addElement(newElement: String): String {
    return if(this.isEmpty()) "$this$newElement" else "$this|$newElement"
}

fun String.removeProject(removedProject: String): String {
    return this.split("|").filter { it != removedProject }.joinToString("|")
}

fun String.removeFeed(removedFeedId: String, selectedProject: String): String {

    val filteredFeed = this.split("|").filter {
        it.getProjectName() == selectedProject
    }.filter {
        it.getFeedId() == removedFeedId
    }
    return this.split("|").filter {
        it != filteredFeed.first()
    }.joinToString("|")

}

fun String.updateFeed(feed: String): String {
    var newFeed = ""
    this.split("|").forEach {
        newFeed += if(it.getFeedId() == feed.getFeedId() && it.getProjectName() == feed.getProjectName()) "$feed|"
                   else "$it|"
    }
    return newFeed.dropLast(1)
}
fun String.getUserName(): String {
    return this.split("userName:")[1].split(";")[0]
}

fun String.getEmail(): String {
    return this.split("email:")[1].split(";")[0]
}

fun String.getPassword(): String {
    return this.split("password:")[1].split(";")[0]
}

// Other Extensions---------------------------------------------------------------------------------
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