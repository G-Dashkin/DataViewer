package com.perfomax.dataviewer.core.navigaion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.perfomax.dataviewer.presentation.auth.navigation.authentication
import com.perfomax.dataviewer.presentation.auth.navigation.logout
import com.perfomax.dataviewer.presentation.auth.navigation.navigateToLogin
import com.perfomax.dataviewer.presentation.auth.navigation.navigateToRegister
import com.perfomax.dataviewer.presentation.auth.navigation.navigateToReset
import com.perfomax.dataviewer.presentation.feeds.navigation.FeedsTopLevelDestination
import com.perfomax.dataviewer.presentation.menu.navigation.menu
import com.perfomax.dataviewer.presentation.home.navigation.HomeTopLevelDestination
import com.perfomax.dataviewer.presentation.menu.navigation.MENU_GRAPH
import com.perfomax.dataviewer.presentation.menu.navigation.navigateToMenu
import com.perfomax.dataviewer.presentation.projects.navigation.ProjectsTopLevelDestination
import com.perfomax.dataviewer.presentation.scanning.navigation.ScanningTopLevelDestination
import com.perfomax.dataviewer.presentation.settings.navigation.SettingsTopLevelDestination
import com.perfomax.dataviewer.presentation.start.navigation.StartDestination
import com.perfomax.dataviewer.presentation.start.navigation.start
import okhttp3.internal.immutableListOf

@Composable
fun DataViewerNavHost() {

    val navController: NavHostController = rememberNavController()
    val startDestination = MENU_GRAPH

    NavHost(
        navController = navController,
        startDestination = StartDestination.route
    ) {

        start(
            onStartNavigate = {
                navController.popBackStack()
                navController.navigate(startDestination)
            }
        )
        authentication(
            onLoginClicked = navController::navigateToMenu,
            onNavigateToLogin = navController::navigateToLogin,
            onNavigateToRegister = navController::navigateToRegister,
            onNavigateToReset = navController::navigateToReset
        )
        menu(
            topLevelDestinations = immutableListOf(
                HomeTopLevelDestination,
                ScanningTopLevelDestination,
                ProjectsTopLevelDestination,
                FeedsTopLevelDestination,
                SettingsTopLevelDestination
            ),
            onNavigateUp = navController::popBackStack,
            onLogout = navController::logout
        )
    }

}