package com.perfomax.dataviewer.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.perfomax.dataviewer.data.utils.Constants.DATASTORE_NAME
import com.perfomax.dataviewer.domain.repository.DatastoreRepository
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "DATASTORE_DATAVIEWER")

class DatastoreRepositoryImpl @Inject constructor(
    private val context: Context
): DatastoreRepository {

    // https://www.youtube.com/watch?v=wkt59jo7Nh0&list=PLBUydZdSJP_2Kst6z3tkfxUWeHyiJZDPY&index=9
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