package com.perfomax.dataviewer.presentation.menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.perfomax.dataviewer.presentation.menu.MenuScreen
import com.perfomax.dataviewer.core.navigaion.NavigationDestination
import com.perfomax.dataviewer.core.navigaion.TopLevelDestination

const val MENU_GRAPH = "menu_graph"

fun NavHostController.navigateToMenu() {
    popBackStack()
    navigate(MenuDestination.route)
}

private object MenuDestination: NavigationDestination {
    override val route = "menu"
}

fun NavGraphBuilder.menu(
    topLevelDestinations: List<TopLevelDestination>,
    onLogout: () -> Unit,
    onNavigateUp: () -> Unit
) {
    navigation(
        startDestination = MenuDestination.route,
        route = MENU_GRAPH
    ) {
        menuInner(
            topLevelDestinations = topLevelDestinations,
            onLogout = onLogout,
        )
    }
}

private fun NavGraphBuilder.menuInner(
    onLogout: () -> Unit,
    topLevelDestinations: List<TopLevelDestination>
) {
    composable(MenuDestination.route) {
        MenuScreen(
            topLevelDestinations = topLevelDestinations,
            onLogout = onLogout,
        )
    }
}