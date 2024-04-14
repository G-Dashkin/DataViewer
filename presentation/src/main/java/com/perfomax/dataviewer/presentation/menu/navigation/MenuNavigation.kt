package com.perfomax.dataviewer.presentation.menu.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.perfomax.dataviewer.navigation.NavigationDestination
import com.perfomax.dataviewer.navigation.TopLevelDestination
import com.perfomax.dataviewer.presentation.menu.MenuContract
import com.perfomax.dataviewer.presentation.menu.MenuScreen
import com.perfomax.dataviewer.presentation.menu.MenuViewModel
import com.perfomax.dataviewer.presentation.scanning.navigation.scanning

const val MENU_GRAPH = "menu_graph"

fun NavHostController.navigateToMenu() {
    popBackStack()
    navigate(MenuDestination.route)
}

object MenuDestination: NavigationDestination {
    override val route = "menu"
}

fun NavGraphBuilder.menu(
    topLevelDestinations: List<TopLevelDestination>,
    onLogout: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToScanning: (String) -> Unit
) {
    navigation(
        startDestination = MenuDestination.route,
        route = MENU_GRAPH
    ) {
        menuInner(
            topLevelDestinations = topLevelDestinations,
            onLogout = onLogout,
            onScanning = onNavigateToScanning
        )
    }
}

private fun NavGraphBuilder.menuInner(
    onLogout: () -> Unit,
    topLevelDestinations: List<TopLevelDestination>,
    onScanning: (String) -> Unit
) {
    composable(MenuDestination.route) {
        val menuViewModel = hiltViewModel<MenuViewModel>()
        val menuUiState by menuViewModel.uiState.collectAsStateWithLifecycle()
        MenuScreen(
            uiState = menuUiState,
            topLevelDestinations = topLevelDestinations,
            onLogout = onLogout,
            updateMainProject = { menuViewModel.intent(MenuContract.Event.UpdateProjectEvent) },
            onScanning = onScanning
        )
    }
}