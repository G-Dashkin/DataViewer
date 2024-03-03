package com.perfomax.dataviewer.presentation.feeds.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.R
import com.perfomax.dataviewer.presentation.feeds.FeedsScreen
import com.perfomax.dataviewer.presentation.menu.NavigationDestination
import com.perfomax.dataviewer.presentation.menu.TopLevelDestination
import com.perfomax.dataviewer.presentation.projects.ProjectsScreen

fun NavGraphBuilder.navigateToFeeds(){
    composable(route = FeedsDestination.route) {
        FeedsScreen()
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