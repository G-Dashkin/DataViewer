package com.perfomax.dataviewer.presentation.feeds.navigation

import androidx.compose.runtime.getValue
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

fun NavGraphBuilder.navigateToFeeds(){
    composable(route = FeedsDestination.route) {
        val feedsViewModel: FeedsViewModel = viewModel()
        val feedsUiState by feedsViewModel.uiState.collectAsStateWithLifecycle()
        FeedsScreen(
            uiState = feedsUiState,
            onFeedFieldChange = { text -> feedsViewModel.intent(FeedsContract.Event.FeedUrlChangeEvent(text)) },
            onAddFeedClick = { feedsViewModel.intent(FeedsContract.Event.AddFeedClickEvent) }
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