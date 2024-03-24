package com.perfomax.dataviewer.core.utils

import android.util.Log

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
