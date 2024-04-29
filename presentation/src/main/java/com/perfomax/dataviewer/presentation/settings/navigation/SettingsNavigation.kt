package com.perfomax.dataviewer.presentation.settings.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.navigation.NavigationDestination
import com.perfomax.dataviewer.navigation.TopLevelDestination
import com.perfomax.dataviewer.presentation.settings.SettingsContract
import com.perfomax.dataviewer.presentation.settings.SettingsScreen
import com.perfomax.dataviewer.presentation.settings.SettingsViewModel
import com.perfomax.ui.R


fun NavGraphBuilder.navigateToSettings(){
    composable(route = SettingsDestination.route) {
        val settingsViewModel = hiltViewModel<SettingsViewModel>()
        val settingsUiState by settingsViewModel.uiState.collectAsStateWithLifecycle()
        SettingsScreen(
            uiState = settingsUiState,
            onSwitchFeedUpdateIntoBackground = { value ->
               settingsViewModel.intent(SettingsContract.Event.SwitchUpdateFeedsIntoBackgroundEvent(value))
            },
            onSetUpdatePeriod = { updatePeriod ->
                settingsViewModel.intent(SettingsContract.Event.UpdatePeriodChangeEvent(updatePeriod))
            },
            onSwitchFeedUpdateWithWIFI = { value ->
                settingsViewModel.intent(SettingsContract.Event.SwitchUpdateFeedsWithWIFIEvent(value))
            },
            onSetPercentForAlert = {  updatePercent ->
                settingsViewModel.intent(SettingsContract.Event.ComparisonPercentChangeEvent(updatePercent))
            },
            onSwitchNotification = { value ->
                settingsViewModel.intent(SettingsContract.Event.SwitchNotificationWorkEvent(value))
            }
        )
    }
}

object SettingsDestination : NavigationDestination {
    override val route = "settings"
}

object SettingsTopLevelDestination : TopLevelDestination {
    override val route = SettingsDestination.route
    override val iconId = R.drawable.ic_bottom_menu_settings
    override val titleId = R.string.settings
}
