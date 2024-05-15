package com.perfomax.dataviewer.presentation.settings

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.domain.usecases.projects.GetSelectedProjectUseCase
import com.perfomax.dataviewer.domain.usecases.settings.GetNotificationUseCase
import com.perfomax.dataviewer.domain.usecases.settings.GetPercentForAlertUseCase
import com.perfomax.dataviewer.domain.usecases.settings.GetUpdateIntoBackgroundUseCase
import com.perfomax.dataviewer.domain.usecases.settings.GetUpdatePeriodUseCase
import com.perfomax.dataviewer.domain.usecases.settings.GetUpdateWithWIFIUseCase
import com.perfomax.dataviewer.domain.usecases.settings.SetNotificationUseCase
import com.perfomax.dataviewer.domain.usecases.settings.SetPercentForAlertUseCase
import com.perfomax.dataviewer.domain.usecases.settings.SetUpdateIntoBackgroundUseCase
import com.perfomax.dataviewer.domain.usecases.settings.SetUpdatePeriodUseCase
import com.perfomax.dataviewer.domain.usecases.settings.SetUpdateWithWIFIUseCase
import com.perfomax.dataviewer.domain.utils.parsAlertPercent
import com.perfomax.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val context: Application,
    private val getNotificationUseCase: GetNotificationUseCase,
    private val getPercentForAlertUseCase: GetPercentForAlertUseCase,
    private val getUpdateIntoBackgroundUseCase: GetUpdateIntoBackgroundUseCase,
    private val getUpdatePeriodUseCase: GetUpdatePeriodUseCase,
    private val getUpdateWithWIFIUseCase: GetUpdateWithWIFIUseCase,
    private val setNotificationUseCase: SetNotificationUseCase,
    private val setPercentForAlertUseCase: SetPercentForAlertUseCase,
    private val setUpdateIntoBackgroundUseCase: SetUpdateIntoBackgroundUseCase,
    private val setUpdatePeriodUseCase: SetUpdatePeriodUseCase,
    private val setUpdateWithWIFIUseCase: SetUpdateWithWIFIUseCase,
    private val getSelectedProjectUseCase: GetSelectedProjectUseCase
): ViewModel(), SettingsContract {

    private val _uiState = MutableStateFlow(SettingsContract.State.initial())
    override val uiState: StateFlow<SettingsContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<SettingsContract.Effect?>(null)
    override val effect: StateFlow<SettingsContract.Effect?> = _effect.asStateFlow()

    init {
        setSettings()
        getSettings()
//        setService()
    }

    override fun intent(event: SettingsContract.Event) {
        when(event) {
            is SettingsContract.Event.SwitchUpdateFeedsIntoBackgroundEvent -> {
                onUpdateFeedsIntoBackgroundChange(event.isUpdate)
            }
            is SettingsContract.Event.UpdatePeriodChangeEvent -> {
                onUpdatePeriodFieldChange(event.updatePeriod)
            }
            is SettingsContract.Event.SwitchUpdateFeedsWithWIFIEvent -> {
                onUpdateFeedsFeedsWithWIFIChange(event.isUpdate)
            }
            is SettingsContract.Event.ComparisonPercentChangeEvent -> {
                onComparisonPercentFieldChange(event.comparisonPercent)
            }
            is SettingsContract.Event.SwitchNotificationWorkEvent -> {
                onNotificationWorkChange(event.isNotificationWor)
            }
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun onUpdateFeedsIntoBackgroundChange(value: Boolean) {
        viewModelScope.launch {
            val selectedProject = getSelectedProjectUseCase.execute()
            val updateFeedsIntoBackgroundValue = "projectName:$selectedProject|updateIntoBackground:$value"
            setUpdateIntoBackgroundUseCase.execute(updateFeedsIntoBackgroundValue)
            _uiState.update { currentState ->
                currentState.copy(
                    isUpdateFeedsIntoBackground = value
                )
            }
        }
    }

    private fun onUpdatePeriodFieldChange(updatePeriod: String) {
        viewModelScope.launch {
            val selectedProject = getSelectedProjectUseCase.execute()
            val updatePeriodValue = "projectName:$selectedProject|updatePeriod:$updatePeriod"
            setUpdatePeriodUseCase.execute(updatePeriodValue)
            _uiState.update { currentState ->
                currentState.copy(
                    updatePeriod = updatePeriod
                )
            }
        }
    }

    private fun onUpdateFeedsFeedsWithWIFIChange(value: Boolean) {
        viewModelScope.launch {
            val selectedProject = getSelectedProjectUseCase.execute()
            val updateWithWIFIValue = "projectName:$selectedProject|updateWithWIFIValue:$value"
            setUpdateWithWIFIUseCase.execute(updateWithWIFIValue)
            _uiState.update { currentState ->
                currentState.copy(
                    isUpdateFeedsWithWIFI = value
                )
            }
        }
    }

    private fun onComparisonPercentFieldChange(comparisonPercent: String) {
        viewModelScope.launch {
            val selectedProject = getSelectedProjectUseCase.execute()
            val comparisonPercentValue = "projectName:$selectedProject|comparisonPercent:${comparisonPercent.parsAlertPercent()}"
            setPercentForAlertUseCase.execute(comparisonPercentValue)
            _uiState.update { currentState ->
                currentState.copy(
                    comparisonAlertPercentName = comparisonPercent
                )
            }
        }
    }

    private fun onNotificationWorkChange(value: Boolean) {
        viewModelScope.launch {
            val selectedProject = getSelectedProjectUseCase.execute()
            val notificationValue = "projectName:$selectedProject|notificationValue:$value"
            setNotificationUseCase.execute(notificationValue)
            _uiState.update { currentState ->
                currentState.copy(
                    isNotificationWork = value
                )
            }
        }
    }

    private fun getSettings() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isUpdateFeedsIntoBackground = getUpdateIntoBackgroundUseCase.execute(),
                    isNotificationWork = getNotificationUseCase.execute(),
                    updatePeriod = getUpdatePeriodUseCase.execute(),
                    isUpdateFeedsWithWIFI = getUpdateWithWIFIUseCase.execute(),
                    comparisonAlertPercent = getPercentForAlertUseCase.execute()
                )
            }
        }
    }

    private fun setSettings() {
        viewModelScope.launch {
            val mapOfAlertPercent = mapOf(Pair(0.1f, context.getString(R.string.different_10)),
                                          Pair(0.2f, context.getString(R.string.different_20)),
                                          Pair(0.3f, context.getString(R.string.different_30)),
                                          Pair(0.4f, context.getString(R.string.different_40)),
                                          Pair(0.5f, context.getString(R.string.different_50)),
                                          Pair(0.6f, context.getString(R.string.different_60)),
                                          Pair(0.7f, context.getString(R.string.different_70)),
                                          Pair(0.8f, context.getString(R.string.different_80)),
                                          Pair(0.9f, context.getString(R.string.different_90)))

            val mapOfUpdateTime  = mapOf(Pair(1, context.getString(R.string.update_time_1)),
                                         Pair(2, context.getString(R.string.update_time_2)),
                                         Pair(3, context.getString(R.string.update_time_3)),
                                         Pair(4, context.getString(R.string.update_time_4)),
                                         Pair(5, context.getString(R.string.update_time_5)),
                                         Pair(6, context.getString(R.string.update_time_6)),
                                         Pair(7, context.getString(R.string.update_time_7)),
                                         Pair(8, context.getString(R.string.update_time_8)),
                                         Pair(9, context.getString(R.string.update_time_9)),
                                         Pair(10, context.getString(R.string.update_time_10)),
                                         Pair(11, context.getString(R.string.update_time_11)),
                                         Pair(12, context.getString(R.string.update_time_12)),
                                         Pair(13, context.getString(R.string.update_time_13)),
                                         Pair(14, context.getString(R.string.update_time_14)),
                                         Pair(15, context.getString(R.string.update_time_15)),
                                         Pair(16, context.getString(R.string.update_time_16)),
                                         Pair(17, context.getString(R.string.update_time_17)),
                                         Pair(18, context.getString(R.string.update_time_18)),
                                         Pair(19, context.getString(R.string.update_time_19)),
                                         Pair(20, context.getString(R.string.update_time_20)),
                                         Pair(21, context.getString(R.string.update_time_21)),
                                         Pair(22, context.getString(R.string.update_time_22)),
                                         Pair(23, context.getString(R.string.update_time_23)),
                                         Pair(24, context.getString(R.string.update_time_24)))

            _uiState.update { currentState ->
                currentState.copy(
                    listOfAlertPercent = mapOfAlertPercent,
                    listOfUpdateTime = mapOfUpdateTime,
                    comparisonAlertPercent = getPercentForAlertUseCase.execute(),
                    comparisonAlertPercentName = mapOfAlertPercent.getOrDefault(getPercentForAlertUseCase.execute().toFloat(), EMPTY)
                )
            }
        }
    }
}