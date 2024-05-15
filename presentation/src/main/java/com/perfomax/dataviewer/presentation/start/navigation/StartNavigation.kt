package com.perfomax.dataviewer.presentation.start.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.navigation.NavigationDestination
import com.perfomax.dataviewer.presentation.start.StartedScreen

fun NavGraphBuilder.start(onStartNavigate:() -> Unit) {
    composable(route = "start") {
        StartedScreen(onStartClicked = onStartNavigate)
    }
}

object StartDestination: NavigationDestination {
    override val route = "start"
}