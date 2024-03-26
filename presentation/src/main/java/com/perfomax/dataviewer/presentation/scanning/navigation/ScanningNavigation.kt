package com.perfomax.dataviewer.presentation.scanning.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.navigation.NavigationDestination
import com.perfomax.dataviewer.navigation.TopLevelDestination
import com.perfomax.dataviewer.presentation.scanning.ScanningScreen
import com.perfomax.ui.R

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
    override val iconId = R.drawable.ic_bottom_menu_search
    override val titleId = R.string.scanning
}