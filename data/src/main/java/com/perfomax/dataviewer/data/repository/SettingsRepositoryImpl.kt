package com.perfomax.dataviewer.data.repository

import android.util.Log
import com.perfomax.dataviewer.data.mappers.toDomainFeed
import com.perfomax.dataviewer.data.network.api.FeedApi
import com.perfomax.dataviewer.data.network.api.Parser
import com.perfomax.dataviewer.data.storage.api.FeedsStorage
import com.perfomax.dataviewer.data.storage.api.SettingsStorage
import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.repository.SettingsRepository
import com.perfomax.dataviewer.domain.utils.addElement
import com.perfomax.dataviewer.domain.utils.parsToString
import com.perfomax.dataviewer.domain.utils.toShortList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.sign

class SettingsRepositoryImpl @Inject constructor(
    private val settingsStorage: SettingsStorage
): SettingsRepository {

    override suspend fun setNotification(setValue: String) {
        settingsStorage.setNotification(setValue)
    }

    override suspend fun setPercentForAlert(percent: String) {
        settingsStorage.setPercentForAlert(percent)
    }

    override suspend fun setUpdateIntoBackground(setValue: String) {
        settingsStorage.setUpdateIntoBackground(setValue)
    }

    override suspend fun setUpdatePeriod(period: String) {
        settingsStorage.setUpdatePeriod(period)

    }

    override suspend fun setUpdateWithWIFI(setValue: String) {
        settingsStorage.setUpdateWithWIFI(setValue)
    }

    override suspend fun getNotification(): Boolean {
        return if(settingsStorage.getNotification().isEmpty()) false
        else settingsStorage.getNotification().split("notificationValue:")[1].toBoolean()
    }

    override suspend fun getPercentForAlert(): String {
        return if(settingsStorage.getPercentForAlert().isEmpty()) ""
        else settingsStorage.getPercentForAlert().split("comparisonPercent:")[1]
    }

    override suspend fun getUpdateIntoBackground(): Boolean {
        return if(settingsStorage.getUpdateIntoBackground().isEmpty()) false
        else settingsStorage.getUpdateIntoBackground().split("updateIntoBackground:")[1].toBoolean()
    }

    override suspend fun getUpdatePeriod(): String {
        return if(settingsStorage.getUpdatePeriod().isEmpty()) ""
        else settingsStorage.getUpdatePeriod().split("updatePeriod:")[1]
    }

    override suspend fun getUpdateWithWIFI(): Boolean {
        return if(settingsStorage.getUpdateWithWIFI().isEmpty()) false
        else settingsStorage.getUpdateWithWIFI().split("updateWithWIFIValue:")[1].toBoolean()
    }
}