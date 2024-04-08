package com.perfomax.dataviewer.data.storage.api

import com.perfomax.dataviewer.domain.models.Feed

interface SettingsStorage {
    suspend fun setNotification(setValue: String)
    suspend fun setPercentForAlert(percent: String)
    suspend fun setUpdateIntoBackground(setValue: String)
    suspend fun setUpdatePeriod(period: String)
    suspend fun setUpdateWithWIFI(setValue: String)
    suspend fun getNotification(): String
    suspend fun getPercentForAlert(): String
    suspend fun getUpdateIntoBackground(): String
    suspend fun getUpdatePeriod(): String
    suspend fun getUpdateWithWIFI(): String
}