package com.example.dataviewer.presentation.menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dataviewer.presentation.menu.MenuScreen
import com.example.dataviewer.presentation.menu.NavigationDestination
import com.example.dataviewer.presentation.menu.TopLevelDestination

const val BOTTOM_MENU_GRAPH = "bottom_menu_graph"

fun NavHostController.navigateToBottomMenu() {
    popBackStack()
    navigate(BottomMenuDestination.route)
}

private object BottomMenuDestination: NavigationDestination {
    override val route = "bottom_menu"
}

fun NavGraphBuilder.bottomMenu(
    topLevelDestinations: List<TopLevelDestination>,
    onLogout: () -> Unit,
    onNavigateUp: () -> Unit
) {
    navigation(startDestination = BottomMenuDestination.route, route = BOTTOM_MENU_GRAPH) {
        bottomMenuInner(
            topLevelDestinations = topLevelDestinations,
            onLogout = onLogout,
        )
    }
}

private fun NavGraphBuilder.bottomMenuInner(
    onLogout: () -> Unit,
    topLevelDestinations: List<TopLevelDestination>
) {
    composable(BottomMenuDestination.route) {
        MenuScreen(
            topLevelDestinations = topLevelDestinations,
            onLogout = onLogout,
        )
    }
}