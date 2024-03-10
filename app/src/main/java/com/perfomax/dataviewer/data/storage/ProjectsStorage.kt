package com.perfomax.dataviewer.data.storage

interface ProjectsStorage {
    fun getAll(): List<String>
    fun add(projectName: String)
    fun remove(id: String)
}