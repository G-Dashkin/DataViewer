package com.perfomax.dataviewer.domain.repository

interface DatastoreRepository {
    suspend fun putString(key:String, value:String)
    suspend fun putBoolean(key:String, value:Boolean)
    suspend fun getString(key:String): String?
    suspend fun clearPreferences(key:String)
}