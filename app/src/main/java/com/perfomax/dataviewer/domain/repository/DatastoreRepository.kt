package com.perfomax.dataviewer.domain.repository

import com.perfomax.dataviewer.data.repository.PhoneBook

interface DatastoreRepository {
    suspend fun putString(key:String,value:String)
    suspend fun putBoolean(key:String,value:Boolean)
    suspend fun getString(key: String):String?
    suspend fun clearPreferences(key: String)

    suspend fun savePhoneBook(phone: String)
    suspend fun getPhoneBook(): String

}