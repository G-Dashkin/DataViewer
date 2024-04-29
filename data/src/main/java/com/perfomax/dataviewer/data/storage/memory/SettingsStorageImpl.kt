package com.perfomax.dataviewer.data.storage.memory

import com.perfomax.dataviewer.data.datastore.api.SettingsDataStore
import com.perfomax.dataviewer.data.storage.api.SettingsStorage
import javax.inject.Inject

class SettingsStorageImpl @Inject constructor(
    private val datastore: SettingsDataStore
): SettingsStorage {
    override suspend fun setNotification(setValue: String) {
        datastore.setNotification(setValue)
    }

    override suspend fun setPercentForAlert(percent: String) {
        datastore.setPercentForAlert(percent)
    }

    override suspend fun setUpdateIntoBackground(setValue: String) {
        datastore.setUpdateIntoBackground(setValue)
    }

    override suspend fun setUpdatePeriod(period: String) {
        datastore.setUpdatePeriod(period)
    }

    override suspend fun setUpdateWithWIFI(setValue: String) {
        datastore.setUpdateWithWIFI(setValue)
    }

    override suspend fun getNotification(): String {
        return datastore.getNotification()
    }

    override suspend fun getPercentForAlert(): String {
        return datastore.getPercentForAlert()
    }

    override suspend fun getUpdateIntoBackground(): String {
        return datastore.getUpdateIntoBackground()
    }

    override suspend fun getUpdatePeriod(): String {
        return datastore.getUpdatePeriod()
    }

    override suspend fun getUpdateWithWIFI(): String {
        return datastore.getUpdateWithWIFI()
    }
}