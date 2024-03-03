package com.example.dataviewer.presentation.bottom_menu

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
import com.example.dataviewer.presentation.bottom_menu.navigation.BOTTOM_MENU_GRAPH
import com.example.dataviewer.presentation.bottom_menu.navigation.bottomMenu
import com.example.dataviewer.presentation.bottom_menu.navigation.navigateToBottomMenu
import com.example.dataviewer.presentation.home.navigation.HomeTopLevelDestination
import com.example.dataviewer.presentation.main.MainViewModel
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
            onLoginClicked = navController::navigateToBottomMenu,
            onNavigateToLogin = navController::navigateToLogin,
            onNavigateToRegister = navController::navigateToRegister,
            onNavigateToReset = navController::navigateToReset
        )
        bottomMenu(
            topLevelDestinations = immutableListOf(
                HomeTopLevelDestination,
                ScanningTopLevelDestination,
                SettingsTopLevelDestination
            ),
//            onNavigateToDetails = navController::navigateToDetails,
            onNavigateUp = navController::popBackStack,
            onLogout = navController::logout,
//            onOffer = navController::offer
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