package com.perfomax.dataviewer.presentation.menu.navigation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.perfomax.dataviewer.presentation.menu.MenuScreen
import com.perfomax.dataviewer.core.navigaion.NavigationDestination
import com.perfomax.dataviewer.core.navigaion.TopLevelDestination
import com.perfomax.dataviewer.presentation.menu.MenuContract
import com.perfomax.dataviewer.presentation.menu.MenuViewModel
import com.perfomax.dataviewer.presentation.projects.ProjectsViewModel

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
            onLogout = onLogout
        )
    }
}

private fun NavGraphBuilder.menuInner(
    onLogout: () -> Unit,
    topLevelDestinations: List<TopLevelDestination>
) {
    composable(MenuDestination.route) {
        val menuViewModel = hiltViewModel<MenuViewModel>()
        val menuUiState by menuViewModel.uiState.collectAsStateWithLifecycle()
        MenuScreen(
            uiState = menuUiState,
            topLevelDestinations = topLevelDestinations,
            onLogout = onLogout,
            onUpdateTitle = { updatedProjectName ->
                menuViewModel.intent(MenuContract.Event.UpdateProjectEvent(updatedProjectName))
            }
        )
    }
}