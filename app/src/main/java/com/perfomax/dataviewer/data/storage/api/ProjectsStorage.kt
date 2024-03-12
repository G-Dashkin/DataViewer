package com.perfomax.dataviewer.data.storage.api

interface ProjectsStorage {
    fun get(): String
    fun add(projectName:String)
    fun remove(projectName:String)
}