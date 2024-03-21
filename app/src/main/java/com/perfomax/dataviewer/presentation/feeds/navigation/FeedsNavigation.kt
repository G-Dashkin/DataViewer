package com.perfomax.dataviewer.presentation.feeds.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.R
import com.perfomax.dataviewer.presentation.feeds.FeedsScreen
import com.perfomax.dataviewer.core.navigaion.NavigationDestination
import com.perfomax.dataviewer.core.navigaion.TopLevelDestination
import com.perfomax.dataviewer.presentation.feeds.FeedsContract
import com.perfomax.dataviewer.presentation.feeds.FeedsViewModel
import com.perfomax.dataviewer.presentation.home.HomeContract
import com.perfomax.dataviewer.presentation.home.HomeViewModel
import com.perfomax.dataviewer.presentation.projects.ProjectsViewModel

fun NavGraphBuilder.navigateToFeeds(){
    composable(route = FeedsDestination.route) {
        val feedsViewModel = hiltViewModel<FeedsViewModel>()
        val projectsUiState by feedsViewModel.uiState.collectAsStateWithLifecycle()
        FeedsScreen(
            uiState = projectsUiState,
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
            onAddNewFeed = { feedsViewModel.intent(FeedsContract.Event.AddNewFeedEvent) }

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