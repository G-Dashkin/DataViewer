package com.example.dataviewer.presentation.scanning.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.dataviewer.R
import com.example.dataviewer.presentation.menu.NavigationDestination
import com.example.dataviewer.presentation.menu.TopLevelDestination
import com.example.dataviewer.presentation.scanning.ScanningScreen

fun NavGraphBuilder.navigateToScanning(){
    composable(route = ScanningDestination.route) {
        ScanningScreen()
    }
}

object ScanningDestination : NavigationDestination {
    override val route = "scanning"
}

object ScanningTopLevelDestination : TopLevelDestination {
    override val route = ScanningDestination.route
    override val iconId = R.drawable.ic_bottom_menu_home
    override val titleId = R.string.scanning
}