package com.perfomax.dataviewer.domain.repository

interface SettingsRepository {
    suspend fun setNotification(setValue: String)
    suspend fun setPercentForAlert(percent: String)
    suspend fun setUpdateIntoBackground(setValue: String)
    suspend fun setUpdatePeriod(period: String)
    suspend fun setUpdateWithWIFI(setValue: String)
    suspend fun getNotification(): Boolean
    suspend fun getPercentForAlert(): String
    suspend fun getUpdateIntoBackground(): Boolean
    suspend fun getUpdatePeriod(): String
    suspend fun getUpdateWithWIFI(): Boolean
}