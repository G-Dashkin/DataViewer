package com.example.dataviewer.presentation.feeds.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.dataviewer.R
import com.example.dataviewer.presentation.feeds.FeedsScreen
import com.example.dataviewer.presentation.menu.NavigationDestination
import com.example.dataviewer.presentation.menu.TopLevelDestination
import com.example.dataviewer.presentation.projects.ProjectsScreen

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