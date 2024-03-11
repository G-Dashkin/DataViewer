package com.perfomax.dataviewer.presentation.projects.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.R
import com.perfomax.dataviewer.core.navigaion.NavigationDestination
import com.perfomax.dataviewer.core.navigaion.TopLevelDestination
import com.perfomax.dataviewer.presentation.home.HomeContract
import com.perfomax.dataviewer.presentation.home.HomeViewModel
import com.perfomax.dataviewer.presentation.projects.ProjectsContract
import com.perfomax.dataviewer.presentation.projects.ProjectsScreen
import com.perfomax.dataviewer.presentation.projects.ProjectsViewModel

fun NavGraphBuilder.navigateToProjects(){
    composable(route = ProjectsDestination.route) {

//        val projectsViewModel : ProjectsViewModel by viewModels()
        // https://www.youtube.com/watch?v=23Gj0z88H2U

        val projectsViewModel: ProjectsViewModel = viewModel()
        val projectsUiState by projectsViewModel.uiState.collectAsStateWithLifecycle()
        ProjectsScreen(
            uiState = projectsUiState,
            onTextChange = { text -> projectsViewModel.intent(ProjectsContract.Event.TextChangeEvent(text)) },
            onTestClick = { projectsViewModel.intent(ProjectsContract.Event.ClickEvent) }
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