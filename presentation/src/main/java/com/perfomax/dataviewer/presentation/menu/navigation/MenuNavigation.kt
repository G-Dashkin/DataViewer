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
    onAuthentication: () -> Unit
) {
    navigation(
        startDestination = MenuDestination.route,
        route = MENU_GRAPH
    ) {
        menuInner(
            topLevelDestinations = topLevelDestinations,
            onAuthentication = onAuthentication
        )
    }
}

private fun NavGraphBuilder.menuInner(
    onAuthentication: () -> Unit,
    topLevelDestinations: List<TopLevelDestination>
) {
    composable(MenuDestination.route) {
        val menuViewModel = hiltViewModel<MenuViewModel>()
        val menuUiState by menuViewModel.uiState.collectAsStateWithLifecycle()
        MenuScreen(
            uiState = menuUiState,
            topLevelDestinations = topLevelDestinations,
            onAuthentication = onAuthentication,
            updateMainProject = { menuViewModel.intent(MenuContract.Event.UpdateProjectEvent) }
        )
    }
}