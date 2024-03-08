package com.perfomax.dataviewer.presentation.projects.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.R
import com.perfomax.dataviewer.core.navigaion.NavigationDestination
import com.perfomax.dataviewer.core.navigaion.TopLevelDestination
import com.perfomax.dataviewer.presentation.projects.ProjectsScreen

fun NavGraphBuilder.navigateToProjects(){
    composable(route = ProjectsDestination.route) {
        ProjectsScreen()
    }
}

object ProjectsDestination : NavigationDestination {
    override val route = "projects"
}

object ProjectsTopLevelDestination : TopLevelDestination {
    override val route = ProjectsDestination.route
    override val iconId = R.drawable.ic_bottom_menu_home
    override val titleId = R.string.projects
}