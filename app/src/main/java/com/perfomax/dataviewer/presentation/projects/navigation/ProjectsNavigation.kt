package com.perfomax.dataviewer.presentation.projects.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.R
import com.perfomax.dataviewer.core.navigaion.NavigationDestination
import com.perfomax.dataviewer.core.navigaion.TopLevelDestination
import com.perfomax.dataviewer.presentation.projects.ProjectsContract
import com.perfomax.dataviewer.presentation.projects.ProjectsScreen
import com.perfomax.dataviewer.presentation.projects.ProjectsViewModel
import androidx.hilt.navigation.compose.hiltViewModel

fun NavGraphBuilder.navigateToProjects(){
    composable(route = ProjectsDestination.route) {

        val projectsViewModel = hiltViewModel<ProjectsViewModel>()
        val projectsUiState by projectsViewModel.uiState.collectAsStateWithLifecycle()
        ProjectsScreen(
            uiState = projectsUiState,
            onProjectNameChange = {
                projectName -> projectsViewModel.intent(ProjectsContract.Event.ProjectNameChangeEvent(projectName))
            },
            onCreateNewProjectClick = {
                projectsViewModel.intent(ProjectsContract.Event.CreateNewProjectClickEvent)
            },
            onGetProjectClick = {
                projectsViewModel.intent(ProjectsContract.Event.GetProjectClickEvent)
            },
            onClearProjectNameClick = {
                projectsViewModel.intent(ProjectsContract.Event.ClearProjectNameEvent)
            }
        )
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