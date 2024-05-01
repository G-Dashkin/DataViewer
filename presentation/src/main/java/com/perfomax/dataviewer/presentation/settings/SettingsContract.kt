package com.perfomax.dataviewer.presentation.settings

import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel

interface SettingsContract:
    UnidirectionalViewModel<SettingsContract.Event, SettingsContract.State, SettingsContract.Effect?> {

    data class State(
        val isUpdateFeedsIntoBackground: Boolean,
        val updatePeriod: String,
        val isUpdateFeedsWithWIFI: Boolean,
        val comparisonAlertPercent: String,
        val comparisonAlertPercentName: String,
        val isNotificationWork: Boolean,
        val listOfAlertPercent: Map<Float, String>,
        val listOfUpdateTime: List<String>,

        ) {
        companion object {
            fun initial(): State = State(
                isUpdateFeedsIntoBackground = false,
                updatePeriod = EMPTY,
                isUpdateFeedsWithWIFI = false,
                comparisonAlertPercent = EMPTY,
                comparisonAlertPercentName = EMPTY,
                isNotificationWork = false,
                listOfAlertPercent = mapOf(),
                listOfUpdateTime = listOf()
            )
            fun notCorrect(): State = State(
                isUpdateFeedsIntoBackground = false,
                updatePeriod = EMPTY,
                isUpdateFeedsWithWIFI = false,
                comparisonAlertPercent = EMPTY,
                comparisonAlertPercentName = EMPTY,
                isNotificationWork = false,
                listOfAlertPercent = mapOf(),
                listOfUpdateTime = listOf()
            )
        }
    }

    sealed interface Event {
        data class SwitchUpdateFeedsIntoBackgroundEvent(val isUpdate: Boolean) : Event
        data class UpdatePeriodChangeEvent(val updatePeriod: String): Event
        data class SwitchUpdateFeedsWithWIFIEvent(val isUpdate: Boolean) : Event
        data class ComparisonPercentChangeEvent(val comparisonPercent: String): Event
        data class SwitchNotificationWorkEvent(val isNotificationWor: Boolean) : Event
    }

    sealed interface Effect {
        data object Click : Effect
    }

}