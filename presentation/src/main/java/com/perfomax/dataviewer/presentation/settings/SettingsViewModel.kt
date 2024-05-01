package com.perfomax.dataviewer.presentation.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
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
        getSettings()
//        setService()
        setSettings()
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
                    comparisonAlertPercent = comparisonPercent
                )
            }
            Log.d("MyLog", "comparisonPercentValue: $comparisonPercentValue")
            Log.d("MyLog", "comparisonPercent: ${_uiState.value.comparisonAlertPercent}")
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
            _uiState.update { currentState ->
                currentState.copy(
                    listOfAlertPercent = mapOf(Pair(0.1f,"Разница 10%"),
                                               Pair(0.2f,"Разница 20%"),
                                               Pair(0.3f,"Разница 30%"),
                                               Pair(0.4f,"Разница 40%"),
                                               Pair(0.5f,"Разница 50%"),
                                               Pair(0.6f,"Разница 60%"),
                                               Pair(0.7f,"Разница 70%"),
                                               Pair(0.8f,"Разница 80%"),
                                               Pair(0.9f,"Разница 90%")),

                    listOfUpdateTime = listOf("Каждый час", "Каждые 2 часа", "Каждые 3 часа",
                                              "Каждые 4 часа", "Каждые 5 часов", "Каждые 6 часов",
                                              "Каждые 7 часов", "Каждые 8 часов","Каждые 9 часов",
                                              "Каждые 10 часов", "Каждые 11 часов", "Каждые 12 часов",
                                              "Каждые 13 часов", "Каждые 14 часов", "Каждые 15 часов",
                                              "Каждые 16 часов", "Каждые 17 часов", "Каждые 18 часов",
                                              "Каждые 19 часов", "Каждые 20 часов", "Каждый 21 час",
                                              "Каждые 22 часа", "Каждые 23 часа", "Каждые 24 часа"),
                    comparisonAlertPercent = getPercentForAlertUseCase.execute()
                )

            }
        }
    }

}