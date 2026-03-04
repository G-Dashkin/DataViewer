package com.dashkin.dataviewer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dashkin.dataviewer.feature.filepicker.presentation.screen.FilePickerScreen
import com.dashkin.dataviewer.feature.nodedetail.presentation.screen.NodeDetailScreen
import com.dashkin.dataviewer.feature.search.presentation.screen.SearchScreen
import com.dashkin.dataviewer.feature.treeview.presentation.screen.TreeViewScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.FilePicker.route
    ) {
        composable(Screen.FilePicker.route) {
            FilePickerScreen(
                onNavigateToTreeView = { fileUri ->
                    navController.navigate(Screen.TreeView.createRoute(fileUri))
                }
            )
        }

        composable(
            route = Screen.TreeView.route,
            arguments = listOf(navArgument("fileUri") { type = NavType.StringType })
        ) { backStackEntry ->
            val fileUri = backStackEntry.arguments?.getString("fileUri")
                ?.decodeNavArg() ?: return@composable
            TreeViewScreen(
                fileUri = fileUri,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToSearch = { uri ->
                    navController.navigate(Screen.Search.createRoute(uri))
                },
                onNavigateToNodeDetail = { nodeId, uri ->
                    navController.navigate(Screen.NodeDetail.createRoute(nodeId, uri))
                }
            )
        }

        composable(
            route = Screen.Search.route,
            arguments = listOf(navArgument("fileUri") { type = NavType.StringType })
        ) { backStackEntry ->
            val fileUri = backStackEntry.arguments?.getString("fileUri")
                ?.decodeNavArg() ?: return@composable
            SearchScreen(
                fileUri = fileUri,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToNode = { nodeId, uri ->
                    navController.navigate(Screen.NodeDetail.createRoute(nodeId, uri))
                }
            )
        }

        composable(
            route = Screen.NodeDetail.route,
            arguments = listOf(
                navArgument("nodeId") { type = NavType.StringType },
                navArgument("fileUri") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nodeId = backStackEntry.arguments?.getString("nodeId") ?: return@composable
            val fileUri = backStackEntry.arguments?.getString("fileUri")
                ?.decodeNavArg() ?: return@composable
            NodeDetailScreen(
                nodeId = nodeId,
                fileUri = fileUri,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
