package com.example.dataviewer.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource


@Composable
fun BottomNavigationBar() {

    val navItems = listOfNavItems
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(

                alwaysShowLabel = true,
                icon = { Icon(item.icon, contentDescription = stringResource(id = item.label)) },
                label = { Text(text = stringResource(id = item.label)) },
                selected = selectedItem == index,

                onClick = {
//                    selectedItem = index
//                    navController.navigate(item.path) {
//                        navController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) { saveState = true }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
                }
            )
        }
    }
}