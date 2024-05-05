package com.perfomax.dataviewer.presentation.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.perfomax.dataviewer.navigation.TopLevelDestination
import com.perfomax.dataviewer.navigation.navigateSingleTopTo
import com.perfomax.dataviewer.presentation.feeds.navigation.navigateToFeeds
import com.perfomax.dataviewer.presentation.home.navigation.HomeDestination
import com.perfomax.dataviewer.presentation.home.navigation.navigateToHome
import com.perfomax.dataviewer.presentation.menu.menu_bottom.DataViewerBottomBar
import com.perfomax.dataviewer.presentation.menu.menu_top.DataViewerTopMenu
import com.perfomax.dataviewer.presentation.projects.navigation.navigateToProjects
import com.perfomax.dataviewer.presentation.scanning.navigation.navigateToScanning
import com.perfomax.dataviewer.presentation.scanning.navigation.scanning
import com.perfomax.dataviewer.presentation.settings.navigation.navigateToSettings
import com.perfomax.dataviewer.ui.theme.DataViewerTheme

@Composable
fun MenuScreen(
    uiState: MenuContract.State,
    topLevelDestinations: List<TopLevelDestination>,
    onLogout: () -> Unit,
    onAuthentication: () -> Unit,
    updateMainProject: () -> Unit,
    onScanning: (String) -> Unit
) {

    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            DataViewerTopMenu(
                titleTopMenu = uiState.selectedProject,
                authUser = uiState.authUser,
                destinations = topLevelDestinations,
                onNavigateToTopLevel = { route ->
                    navController.navigateSingleTopTo(route)
                },
                onAuthentication = onAuthentication,
                updateMainProject = updateMainProject
            )
        },
        bottomBar = {
            DataViewerBottomBar(
                currentDestination = currentRoute,
                destinations = topLevelDestinations,
                onNavigateToTopLevel = { route ->
                    if (route == "scanning/{feed_url}") navController.navigateToScanning(" ")
                    else navController.navigateSingleTopTo(route)
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            NavHost(
                navController = navController,
                startDestination = HomeDestination.route
            ) {
                navigateToHome(
                    onNavigateToScan = { feed ->
                        navController.navigateToScanning(feed)
                    }
                )
                scanning()
                navigateToSettings()
                navigateToProjects(onUpdateTitle = updateMainProject)
                navigateToFeeds()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomMenuScreenScreenPreview() {
    DataViewerTheme {
//        BottomMenuScreen(
//            topLevelDestinations = immutableListOf(
//                HomeTopLevelDestination,
//                OfferTopLevelDestination,
//                CartTopLevelDestination,
//                ProfileTopLevelDestination),
//            onItemSelected = {},
//            onLogout = {},
//            onOffer = {}
//        )
    }
}