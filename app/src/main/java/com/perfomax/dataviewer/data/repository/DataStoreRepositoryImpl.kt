package com.perfomax.dataviewer.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.perfomax.dataviewer.domain.repository.DatastoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name="DATASTORE_NAME")

class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context
): DatastoreRepository {

    companion object {
        val NAME = stringPreferencesKey("NAME")
        val PHONE_NUMBER = stringPreferencesKey("PHONE")
        val address = stringPreferencesKey("ADDRESS")
    }

    override suspend fun savePhoneBook(phone: String) {
        val preferenceKay = stringPreferencesKey("key")
        context.dataStore.edit { phonebook ->
            phonebook[preferenceKay] = phone
        }
    }

    override suspend fun getPhoneBook(): String {
        val preferenceKay = stringPreferencesKey("key")
        val preference = context.dataStore.data.first()
        return preference[preferenceKay]!!
    }

    override suspend fun putString(key: String, value: String) {
        val prefereneKay = stringPreferencesKey(key)
        context.dataStore.edit {
            it[prefereneKay] = value
        }
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        val prefernceKey = booleanPreferencesKey(key)
        context.dataStore.edit {
            it[prefernceKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        return  try {
            val preferenceKey = stringPreferencesKey(key)
            val preference = context.dataStore.data.first()
            preference[preferenceKey]
        }catch (e:Exception){
            e.printStackTrace()
            null
        }
    }

    override suspend fun clearPreferences(key: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit {
            if (it.contains(preferenceKey)){
                it.remove(preferenceKey)
            }
        }
    }
}