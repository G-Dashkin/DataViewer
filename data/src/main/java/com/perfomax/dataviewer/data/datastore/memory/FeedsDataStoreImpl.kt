package com.perfomax.dataviewer.data.datastore.memory

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.perfomax.dataviewer.data.datastore.api.FeedsDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(FeedsDataStoreImpl.FEEDS_DATASTORE)

class FeedsDataStoreImpl @Inject constructor(
    private val context: Context
): FeedsDataStore {

    companion object {
        const val FEEDS_DATASTORE = "FEEDS_DATASTORE"
        val FEEDS_LIST = stringPreferencesKey("FEEDS_LIST")
    }

    override suspend fun getAllFeeds(): String {
        val preference = context.dataStore.data.first()
        return preference[FEEDS_LIST]?:""
    }

    override suspend fun updateFeedsList(feedName: String) {
        context.dataStore.edit { preferences ->
            preferences[FEEDS_LIST] = feedName
        }
    }
}