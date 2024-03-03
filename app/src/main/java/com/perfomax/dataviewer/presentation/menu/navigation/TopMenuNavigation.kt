package com.perfomax.dataviewer.presentation.menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.perfomax.dataviewer.presentation.menu.MenuScreen
import com.perfomax.dataviewer.presentation.menu.NavigationDestination
import com.perfomax.dataviewer.presentation.menu.TopLevelDestination

const val TOP_MENU_GRAPH = "top_menu_graph"

fun NavHostController.navigateToTopMenu() {
    popBackStack()
    navigate(TopMenuDestination.route)
}

private object TopMenuDestination: NavigationDestination {
    override val route = "top_menu"
}

fun NavGraphBuilder.topMenu(
    topLevelDestinations: List<TopLevelDestination>,
    onLogout: () -> Unit
) {
    navigation(startDestination = TopMenuDestination.route, route = TOP_MENU_GRAPH) {
        topMenuInner(
            topLevelDestinations = topLevelDestinations,
            onLogout = onLogout,
        )
    }
}

private fun NavGraphBuilder.topMenuInner(
    topLevelDestinations: List<TopLevelDestination>,
    onLogout: () -> Unit
) {
    composable(TopMenuDestination.route) {
        MenuScreen(
            topLevelDestinations = topLevelDestinations,
            onLogout = onLogout
        )
    }
}