package com.perfomax.dataviewer.presentation.home.navigation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.perfomax.dataviewer.navigation.NavigationDestination
import com.perfomax.dataviewer.navigation.TopLevelDestination
import com.perfomax.dataviewer.navigation.navigateSingleTopTo
import com.perfomax.dataviewer.presentation.feeds.navigation.navigateToFeeds
import com.perfomax.dataviewer.presentation.home.HomeContract
import com.perfomax.dataviewer.presentation.home.HomeScreen
import com.perfomax.dataviewer.presentation.home.HomeViewModel
import com.perfomax.dataviewer.presentation.projects.navigation.navigateToProjects
import com.perfomax.dataviewer.presentation.scanning.navigation.scanning
import com.perfomax.dataviewer.ui.base.useEffects
import com.perfomax.ui.R

fun NavGraphBuilder.navigateToHome(
    onNavigateToScan: (String) -> Unit
){

    composable(route = HomeDestination.route) {
        val homeViewModel = hiltViewModel<HomeViewModel>()
        val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

        HomeScreen (
            uiState = homeUiState,
            updateFeedsList = { homeViewModel.intent(HomeContract.Event.UpdateFeedsListEvent) },
            updateBackgroundUpdate = { homeViewModel.intent(HomeContract.Event.UpdateBackgroundEvent)},
            onUpdateFeedsClick = { homeViewModel.intent(HomeContract.Event.CountFeedElementEvent) },
            onClickFeedElement = { clickedFeed ->
                homeViewModel.intent(HomeContract.Event.ClickFeedNameEvent(clickedFeed))
            },
            onFindFeedElement = { findingFeedElementName ->
                homeViewModel.intent(HomeContract.Event.ClickFindFeedElement(findingFeedElementName))
            },
            onFindSelectedElement = { onFeedSelected ->
                onNavigateToScan.invoke(onFeedSelected)
            },
            onClickUpdateFeed = { homeViewModel.intent(HomeContract.Event.ClickUpdateFeedEvent) },
            onChangeFeed = { changingFeed ->
                homeViewModel.intent(HomeContract.Event.ChangeFeedEvent(changingFeed))
            },
            onCloseDialogClick = { homeViewModel.intent(HomeContract.Event.CloseDialogClickEvent) }
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
