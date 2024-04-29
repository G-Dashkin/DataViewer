package com.perfomax.dataviewer.presentation.projects.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.navigation.NavigationDestination
import com.perfomax.dataviewer.navigation.TopLevelDestination
import com.perfomax.dataviewer.presentation.projects.ProjectsContract
import com.perfomax.dataviewer.presentation.projects.ProjectsScreen
import com.perfomax.dataviewer.presentation.projects.ProjectsViewModel
import com.perfomax.ui.R

fun NavGraphBuilder.navigateToProjects(
    onUpdateTitle:() -> Unit
){
    composable(route = ProjectsDestination.route) {

        val projectsViewModel = hiltViewModel<ProjectsViewModel>()
        val projectsUiState by projectsViewModel.uiState.collectAsStateWithLifecycle()
        ProjectsScreen(
            uiState = projectsUiState,
            onProjectNameChange = { projectName ->
                projectsViewModel.intent(ProjectsContract.Event.ProjectNameChangeEvent(projectName))
            },
            onCreateNewProjectClick = {
                projectsViewModel.intent(ProjectsContract.Event.CreateNewProjectClickEvent)
            },
            onSelectRemovedProjectNameClick = { removedProjectName ->
                projectsViewModel.intent(ProjectsContract.Event.SelectRemovedProjectEvent(removedProjectName))
            },
            onRemoveProjectClick = {
                projectsViewModel.intent(ProjectsContract.Event.RemoveProjectClickEvent)
            },
            onSelectProjectClick = { selectedProject ->
                projectsViewModel.intent(ProjectsContract.Event.SelectProjectEvent(selectedProject))
            },
            onOpenDialogCreateClick = {
                projectsViewModel.intent(ProjectsContract.Event.OpenDialogCreateEvent)
            },
            onCloseDialogCreateClick = {
                projectsViewModel.intent(ProjectsContract.Event.CloseDialogCreateEvent)
            },
            onCloseDialogRemoveClick = {
                projectsViewModel.intent(ProjectsContract.Event.CloseDialogRemoveEvent)
            },
            onUpdateProjectClick = onUpdateTitle
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