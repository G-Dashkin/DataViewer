package com.example.dataviewer.presentation.bottom_menu

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
import com.example.dataviewer.core.ui.theme.DataViewerTheme
import com.example.dataviewer.presentation.home.navigation.HomeDestination
import com.example.dataviewer.presentation.home.navigation.navigateToHome
import com.example.dataviewer.presentation.scanning.navigation.navigateToScanning
import com.example.dataviewer.presentation.settings.navigation.navigateToSettings

class BottomMenuScreen {
}

@Composable
fun BottomMenuScreen(
    topLevelDestinations: List<TopLevelDestination>,
//    onItemSelected: (String) -> Unit,
    onLogout: () -> Unit,
//    onOffer: () -> Unit
) {

    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopMenuNavigation(
                destinations = topLevelDestinations,
                onNavigateToTopLevel = { route ->
                    navController.navigateSingleTopTo(route)
                }

            )
        },
        bottomBar = {
            DataViewerBottomBar(
                currentDestination = currentRoute,
                destinations = topLevelDestinations,
                onNavigateToTopLevel = { route ->
                    navController.navigateSingleTopTo(route)
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            NavHost(
                navController = navController,
                startDestination = HomeDestination.route
            ) {
                navigateToHome()
                navigateToScanning()
                navigateToSettings()
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