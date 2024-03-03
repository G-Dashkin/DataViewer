package com.example.dataviewer.presentation.projects.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.dataviewer.R
import com.example.dataviewer.presentation.menu.NavigationDestination
import com.example.dataviewer.presentation.menu.TopLevelDestination
import com.example.dataviewer.presentation.projects.ProjectsScreen

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