package com.perfomax.dataviewer.presentation.feeds.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.navigation.NavigationDestination
import com.perfomax.dataviewer.navigation.TopLevelDestination
import com.perfomax.dataviewer.presentation.feeds.FeedsContract
import com.perfomax.dataviewer.presentation.feeds.FeedsScreen
import com.perfomax.dataviewer.presentation.feeds.FeedsViewModel
import com.perfomax.ui.R

fun NavGraphBuilder.navigateToFeeds(){
    composable(route = FeedsDestination.route) {
        val feedsViewModel = hiltViewModel<FeedsViewModel>()
        val feedsUiState by feedsViewModel.uiState.collectAsStateWithLifecycle()
        FeedsScreen(
            uiState = feedsUiState,
            onFeedUrlFieldChange = { feedUrl ->
                feedsViewModel.intent(FeedsContract.Event.FeedUrlChangeEvent(feedUrl))
            },
            onAddFeedClick = { feedsViewModel.intent(FeedsContract.Event.AddFeedClickEvent) },
            onSelectFeedElement = { feedElement ->
                feedsViewModel.intent(FeedsContract.Event.SelectFeedElementEvent(feedElement))
            },
            onOpenDialogSelectedFeedElementClick = {
                feedsViewModel.intent(FeedsContract.Event.OpenDialogSelectedFeedElementEvent)
            },
            onCloseDialogSelectedFeedElement = {
                feedsViewModel.intent(FeedsContract.Event.CloseDialogSelectedFeedElementEvent)
            },
            onFeedNameFieldChange = { feedName ->
                feedsViewModel.intent(FeedsContract.Event.FeedNameEvent(feedName))
            },
            onAddNewFeed = { feedsViewModel.intent(FeedsContract.Event.AddNewFeedEvent) },
            onSelectRemovedFeedNameClick = { removedFeedName ->
                feedsViewModel.intent(FeedsContract.Event.SelectRemovedFeedEvent(removedFeedName))
            },
            onCloseDialogRemoveFeedClick = {
                feedsViewModel.intent(FeedsContract.Event.CloseDialogRemoveEvent)
            },
            onRemoveFeedClick = {
                feedsViewModel.intent(FeedsContract.Event.RemoveFeedClickEvent)
            },
            updateProject = {
                feedsViewModel.intent(FeedsContract.Event.UpdateProjectEvent)
            },
            onSwitchToFeedsListClick = {
                feedsViewModel.intent(FeedsContract.Event.SwitchScreenToFeedsListEvent)
            },
            onCloseDialogFeedUrlError = {
                feedsViewModel.intent(FeedsContract.Event.CloseDialogFeedUrlErrorEvent)
            }
        )
    }
}

object FeedsDestination : NavigationDestination {
    override val route = "feeds"
}

object FeedsTopLevelDestination : TopLevelDestination {
    override val route = FeedsDestination.route
    override val iconId = R.drawable.ic_bottom_menu_home
    override val titleId = R.string.feeds
}