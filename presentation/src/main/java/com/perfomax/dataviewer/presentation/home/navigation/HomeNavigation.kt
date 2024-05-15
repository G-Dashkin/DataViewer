package com.perfomax.dataviewer.presentation.home.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.navigation.NavigationDestination
import com.perfomax.dataviewer.navigation.TopLevelDestination
import com.perfomax.dataviewer.presentation.home.HomeContract
import com.perfomax.dataviewer.presentation.home.HomeScreen
import com.perfomax.dataviewer.presentation.home.HomeViewModel
import com.perfomax.dataviewer.ui.base.useEffects
import com.perfomax.ui.R

fun NavGraphBuilder.navigateToHome(
    onNavigateToScan: (String) -> Unit
){

    composable(route = HomeDestination.route) {
        val homeViewModel = hiltViewModel<HomeViewModel>()
        val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
        val homeEffects by homeViewModel.effect.collectAsStateWithLifecycle()

        homeViewModel.useEffects {
            when(homeEffects) {
                is HomeContract.Effect.Scanning -> onNavigateToScan((homeEffects as HomeContract.Effect.Scanning).feedUrl)
                null -> Unit
            }
        }

        HomeScreen(
            uiState = homeUiState,
            updateFeedsList = { homeViewModel.intent(HomeContract.Event.UpdateFeedsListEvent) },
            updateBackgroundUpdate = { homeViewModel.intent(HomeContract.Event.UpdateBackgroundEvent)},
            onClickUpdateFeedsList = { homeViewModel.intent(HomeContract.Event.CountFeedElementEvent) },
            onClickFeedElement = { clickedFeed ->
                homeViewModel.intent(HomeContract.Event.ClickFeedNameEvent(clickedFeed))
            },
            onFindSelectedElement = { onFeedSelected ->
                homeViewModel.intent(HomeContract.Event.ScanningEvent(onFeedSelected))
            },
            onClickUpdateFeed = { homeViewModel.intent(HomeContract.Event.UpdateFeedEvent) },
            onCloseDialogClick = { homeViewModel.intent(HomeContract.Event.CloseDialogClickEvent) },
            onCloseDialogIsConnected = { homeViewModel.intent(HomeContract.Event.CloseDialogIsConnectedEvent) }
        )
    }
}

object HomeDestination : NavigationDestination {
    override val route = "home"
}

object HomeTopLevelDestination : TopLevelDestination {
    override val route = HomeDestination.route
    override val iconId = R.drawable.ic_bottom_menu_home
    override val titleId = R.string.home
}
