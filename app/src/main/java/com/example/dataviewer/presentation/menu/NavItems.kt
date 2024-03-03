package com.example.dataviewer.presentation.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dataviewer.R

data class NavItem (
    val label: Int,
    val icon: ImageVector,
    val route: String
)

val listOfNavItems = listOf(
    NavItem(
        label = R.string.home,
        icon = Icons.Default.Home,
        route = Screens.HOME.name
    ),
    NavItem(
        label = R.string.scanning,
        icon = Icons.Default.Search,
        route = Screens.SCANNING.name
    ),
    NavItem(
        label = R.string.settings,
        icon = Icons.Default.Settings,
        route = Screens.SETTINGS.name
    )
)