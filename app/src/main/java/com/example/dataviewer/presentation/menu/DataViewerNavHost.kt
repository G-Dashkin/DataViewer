package com.example.dataviewer.presentation.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.dataviewer.presentation.auth.navigation.AUTH_GRAPH
import com.example.dataviewer.presentation.auth.navigation.authentication
import com.example.dataviewer.presentation.auth.navigation.logout
import com.example.dataviewer.presentation.auth.navigation.navigateToLogin
import com.example.dataviewer.presentation.auth.navigation.navigateToRegister
import com.example.dataviewer.presentation.auth.navigation.navigateToReset
import com.example.dataviewer.presentation.feeds.navigation.FeedsTopLevelDestination
import com.example.dataviewer.presentation.menu.navigation.BOTTOM_MENU_GRAPH
import com.example.dataviewer.presentation.menu.navigation.bottomMenu
import com.example.dataviewer.presentation.menu.navigation.navigateToMenuScreen
import com.example.dataviewer.presentation.home.navigation.HomeTopLevelDestination
import com.example.dataviewer.presentation.main.MainViewModel
import com.example.dataviewer.presentation.menu.navigation.topMenu
import com.example.dataviewer.presentation.projects.navigation.ProjectsTopLevelDestination
import com.example.dataviewer.presentation.scanning.navigation.ScanningTopLevelDestination
import com.example.dataviewer.presentation.settings.navigation.SettingsTopLevelDestination
import com.example.dataviewer.presentation.start.navigation.StartDestination
import com.example.dataviewer.presentation.start.navigation.start
import okhttp3.internal.immutableListOf

@Composable
fun DataViewerNavHost() {

    val navController: NavHostController = rememberNavController()
    val viewModel: MainViewModel = viewModel()

    val authentication by viewModel.authenticated.collectAsState()
    val startDestination = if (authentication) BOTTOM_MENU_GRAPH else AUTH_GRAPH

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
            onLoginClicked = navController::navigateToMenuScreen,
            onNavigateToLogin = navController::navigateToLogin,
            onNavigateToRegister = navController::navigateToRegister,
            onNavigateToReset = navController::navigateToReset
        )
        topMenu(
            topLevelDestinations = immutableListOf(
                HomeTopLevelDestination,
                ProjectsTopLevelDestination,
                FeedsTopLevelDestination,
                SettingsTopLevelDestination
            ),
            onLogout = navController::logout
        )
        bottomMenu(
            topLevelDestinations = immutableListOf(
                HomeTopLevelDestination,
                ScanningTopLevelDestination,
                SettingsTopLevelDestination
            ),
            onNavigateUp = navController::popBackStack,
            onLogout = navController::logout
        )

//        bottomMenu(
//            topLevelDestinations = immutableListOf(
//                HomeTopLevelDestination,
//                OfferTopLevelDestination,
//                CartTopLevelDestination,
//                ProfileTopLevelDestination
//            ),
//            onNavigateToDetails = navController::navigateToDetails,
//            onNavigateUp = navController::popBackStack,
//            onLogout = navController::logout,
//            onOffer = navController::offer
//        )
//        offer()
    }

}