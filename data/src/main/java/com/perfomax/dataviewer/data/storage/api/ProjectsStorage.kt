package com.perfomax.dataviewer.data.storage.api

interface ProjectsStorage {
    suspend fun add(projectName:String)
    suspend fun getAll(): List<String>
    suspend fun remove(projectName:String)
    suspend fun select(projectName:String)
    suspend fun getSelected(): String
}