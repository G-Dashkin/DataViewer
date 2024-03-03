package com.perfomax.dataviewer.presentation.start.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.presentation.menu.NavigationDestination
import com.perfomax.dataviewer.presentation.start.StartedScreen

object StartDestination: NavigationDestination {
    override val route = "start"
}

fun NavGraphBuilder.start(onStartNavigate:() -> Unit) {
    composable(route = "start") {
        StartedScreen(onStartClicked = onStartNavigate)
    }
}

