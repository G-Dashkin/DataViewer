package com.example.dataviewer.presentation.bottom_menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dataviewer.presentation.bottom_menu.BottomMenuScreen
import com.example.dataviewer.presentation.bottom_menu.NavigationDestination
import com.example.dataviewer.presentation.bottom_menu.TopLevelDestination

const val BOTTOM_MENU_GRAPH = "bottom_menu_graph"

fun NavHostController.navigateToBottomMenu() {
    popBackStack()
    navigate(BottomMenuDestination.route)
}

private object BottomMenuDestination: NavigationDestination {
    override val route = "bottom_menu"
}

fun NavGraphBuilder.bottomMenu(
    topLevelDestinations: List<TopLevelDestination>,
//    onNavigateToDetails: (String) -> Unit,
    onLogout: () -> Unit,
//    onOffer: () -> Unit,
    onNavigateUp: () -> Unit
) {
    navigation(startDestination = BottomMenuDestination.route, route = BOTTOM_MENU_GRAPH) {
        bottomMenuInner(
//            navigateToDetails = onNavigateToDetails,
            topLevelDestinations = topLevelDestinations,
            onLogout = onLogout,
//            onOffer = onOffer
        )
//        details(
//            onNavigateUp = onNavigateUp
//        )
    }
}

private fun NavGraphBuilder.bottomMenuInner(
//    navigateToDetails: (String) -> Unit,
    onLogout: () -> Unit,
//    onOffer: () -> Unit,
    topLevelDestinations: List<TopLevelDestination>
) {
    composable(BottomMenuDestination.route) {
        BottomMenuScreen(
            topLevelDestinations = topLevelDestinations,
//            onItemSelected = navigateToDetails,
            onLogout = onLogout,
//            onOffer = onOffer
        )
    }
}


//@Composable
//fun BottomMenuNavigation() {
//
//    val navItems = listOfNavItems
//    var selectedItem by rememberSaveable { mutableStateOf(0) }
//
//    NavigationBar {
//        navItems.forEachIndexed { index, item ->
//            NavigationBarItem(
//
//                alwaysShowLabel = true,
//                icon = { Icon(item.icon, contentDescription = stringResource(id = item.label)) },
//                label = { Text(text = stringResource(id = item.label)) },
//                selected = selectedItem == index,
//
//                onClick = {
////                    selectedItem = index
////                    navController.navigate(item.path) {
////                        navController.graph.startDestinationRoute?.let { route ->
////                            popUpTo(route) { saveState = true }
////                        }
////                        launchSingleTop = true
////                        restoreState = true
////                    }
//                }
//            )
//        }
//    }
//}