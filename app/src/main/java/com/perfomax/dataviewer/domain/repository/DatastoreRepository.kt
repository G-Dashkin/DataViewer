package com.perfomax.dataviewer.domain.repository

interface DatastoreRepository {
    suspend fun putString(key:String,value:String)
    suspend fun getString(key: String):String?
    suspend fun removePreferences(key: String)

//    suspend fun putBoolean(key:String,value:Boolean)

//
//    suspend fun savePhoneBook(phone: String)
//    suspend fun getPhoneBook(): String
}