package com.perfomax.dataviewer.domain.utils


fun String.parsToList(): List<String> {
    return this.split("|")
}

fun String.parsToListByProject(): List<String> {
    return this.split("|")
}

fun String.addElement(newProject: String): String {
    return if(this.isEmpty()) "$this$newProject" else "$this|$newProject"
}

fun String.removeProject(removedProject: String): String {
    return this.split("|").filter { it != removedProject }.joinToString("|")
}

fun String.removeFeed(feedName: String): String {
    return this.split("|").filter {
        it.split("feedName:")[1].split(";")[0] != feedName
    }.joinToString("|")
}

fun String.getFeedName(): String {
    return this.split("feedName:")[1].split(";")[0]
}

fun String.getFeedElement(): String {
    return this.split("feedElement:")[1].split(";")[0]
}

fun String.getFeedUrl(): String {
    return this.split("feedUrl:")[1].split(";")[0]
}
