package com.example.dataviewer.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dataviewer.presentation.navigation.TopBar
import com.example.dataviewer.presentation.feeds.FeedsScreen
import com.example.dataviewer.presentation.home.HomeScreen
import com.example.dataviewer.presentation.navigation.Screens
import com.example.dataviewer.presentation.navigation.listOfNavItems
import com.example.dataviewer.presentation.projects.ProjectsScreen
import com.example.dataviewer.presentation.scanning.ScanningScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    state: MainUiState,
    onHomeScreen: () -> Unit = {},
    onProjectsScreen: () -> Unit = {},
    onFeedsScreen: () -> Unit = {}
) {

    val navController = rememberNavController()

    Scaffold (
        topBar = {
            TopBar(
                onHomeClick = onHomeScreen,
                onFeedsClick = onFeedsScreen,
                onProjectsClick = onProjectsScreen
            )
            when (state.screen) {

                Screens.HOME -> {
                    HomeScreen()
                }
                Screens.SCANNING -> {
                    ScanningScreen()
                }
                Screens.SETTINGS -> {

                }

                Screens.PROJECTS -> {
                    ProjectsScreen()
                }

                Screens.FEEDS -> {
                    FeedsScreen()
                }
            }
        },
        bottomBar =  {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                listOfNavItems.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {it.route == navItem.route } == true,
                        onClick = {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                      },
                        icon = { Icon(imageVector = navItem.icon, contentDescription = null ) },
                        label = { Text(text = stringResource(id = navItem.label)) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = Screens.HOME.name
        ){
            composable(route = Screens.HOME.name) { HomeScreen() }
            composable(route = Screens.SCANNING.name) { ScanningScreen() }
            composable(route = Screens.SETTINGS.name) {}
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MainScreen(state = MainUiState(screen = Screens.HOME))
}