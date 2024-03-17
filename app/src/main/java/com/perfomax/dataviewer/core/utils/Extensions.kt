package com.perfomax.dataviewer.core.utils

import android.util.Log

fun String.parsToList(): List<String> {
    return this.split("|")
}

fun String.addElement(newProject: String): String {
    return if(this.isEmpty()) "$this$newProject" else "$this|$newProject"
}

fun String.removeElement(removedProject: String): String {
    return this.split("|").filter { it != removedProject }.joinToString("|")
}