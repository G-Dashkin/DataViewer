package com.perfomax.dataviewer.presentation.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.perfomax.dataviewer.presentation.auth.navigation.AUTH_GRAPH
import com.perfomax.dataviewer.presentation.auth.navigation.authentication
import com.perfomax.dataviewer.presentation.auth.navigation.logout
import com.perfomax.dataviewer.presentation.auth.navigation.navigateToLogin
import com.perfomax.dataviewer.presentation.auth.navigation.navigateToRegister
import com.perfomax.dataviewer.presentation.auth.navigation.navigateToReset
import com.perfomax.dataviewer.presentation.feeds.navigation.FeedsTopLevelDestination
import com.perfomax.dataviewer.presentation.menu.navigation.BOTTOM_MENU_GRAPH
import com.perfomax.dataviewer.presentation.menu.navigation.bottomMenu
import com.perfomax.dataviewer.presentation.home.navigation.HomeTopLevelDestination
import com.perfomax.dataviewer.presentation.main.MainViewModel
import com.perfomax.dataviewer.presentation.menu.navigation.navigateToBottomMenu
import com.perfomax.dataviewer.presentation.menu.navigation.topMenu
import com.perfomax.dataviewer.presentation.projects.navigation.ProjectsTopLevelDestination
import com.perfomax.dataviewer.presentation.scanning.navigation.ScanningTopLevelDestination
import com.perfomax.dataviewer.presentation.settings.navigation.SettingsTopLevelDestination
import com.perfomax.dataviewer.presentation.start.navigation.StartDestination
import com.perfomax.dataviewer.presentation.start.navigation.start
import okhttp3.internal.immutableListOf

@Composable
fun DataViewerNavHost() {

    val navController: NavHostController = rememberNavController()
//    val viewModel: MainViewModel = viewModel()

//    val authentication by viewModel.authenticated.collectAsState()
//    val startDestination = if (authentication) BOTTOM_MENU_GRAPH else AUTH_GRAPH

    val startDestination = BOTTOM_MENU_GRAPH

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
//        topMenu(
//            topLevelDestinations = immutableListOf(
//                HomeTopLevelDestination,
//                ProjectsTopLevelDestination,
//                FeedsTopLevelDestination,
//                SettingsTopLevelDestination
//            ),
//            onLogout = navController::logout
//        )
        bottomMenu(
            topLevelDestinations = immutableListOf(
                HomeTopLevelDestination,
                ScanningTopLevelDestination,
                ProjectsTopLevelDestination,
                FeedsTopLevelDestination,
                SettingsTopLevelDestination,
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