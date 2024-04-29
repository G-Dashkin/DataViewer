package com.perfomax.dataviewer.data.datastore.memory

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.perfomax.dataviewer.data.datastore.api.SettingsDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(SettingsDataStoreImpl.SETTINGS_DATASTORE)

class SettingsDataStoreImpl @Inject constructor(
    private val context: Context
): SettingsDataStore {

    companion object {
        const val SETTINGS_DATASTORE = "SETTINGS_DATASTORE"
        val NOTIFICATION = stringPreferencesKey("NOTIFICATION")
        val UPDATE_WITH_WIFI = stringPreferencesKey("UPDATE_WITH_WIFI")
        val UPDATE_INTO_BACKGROUND = stringPreferencesKey("UPDATE_INTO_BACKGROUND")

        val PERCENT_FOR_ALERT = stringPreferencesKey("PERCENT_FOR_ALERT")
        val UPDATE_PERIOD = stringPreferencesKey("UPDATE_PERIOD")
    }

    override suspend fun setNotification(setValue: String) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATION] = setValue
        }
    }

    override suspend fun setPercentForAlert(percent: String) {
        context.dataStore.edit { preferences ->
            preferences[PERCENT_FOR_ALERT] = percent
        }
    }

    override suspend fun setUpdateIntoBackground(setValue: String) {
        context.dataStore.edit { preferences ->
            preferences[UPDATE_INTO_BACKGROUND] = setValue
        }
    }

    override suspend fun setUpdatePeriod(updatePeriod: String) {
        context.dataStore.edit { preferences ->
            preferences[UPDATE_PERIOD] = updatePeriod
        }
    }

    override suspend fun setUpdateWithWIFI(setValue: String) {
        context.dataStore.edit { preferences ->
            preferences[UPDATE_WITH_WIFI] = setValue
        }
    }

    override suspend fun getNotification(): String {
        val preference = context.dataStore.data.first()
        return preference[NOTIFICATION]?:""
    }

    override suspend fun getPercentForAlert(): String {
        val preference = context.dataStore.data.first()
        return preference[PERCENT_FOR_ALERT]?:""
    }

    override suspend fun getUpdateIntoBackground(): String {
        val preference = context.dataStore.data.first()
        return preference[UPDATE_INTO_BACKGROUND]?:""
    }

    override suspend fun getUpdatePeriod(): String {
        val preference = context.dataStore.data.first()
        return preference[UPDATE_PERIOD]?:""
    }

    override suspend fun getUpdateWithWIFI(): String {
        val preference = context.dataStore.data.first()
        return preference[UPDATE_WITH_WIFI]?:""
    }
}