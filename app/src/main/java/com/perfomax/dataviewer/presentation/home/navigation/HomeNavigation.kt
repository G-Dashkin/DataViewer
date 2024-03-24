package com.perfomax.dataviewer.presentation.home.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.R
import com.perfomax.dataviewer.core.navigaion.NavigationDestination
import com.perfomax.dataviewer.core.navigaion.TopLevelDestination
import com.perfomax.dataviewer.presentation.feeds.FeedsViewModel
import com.perfomax.dataviewer.presentation.home.HomeContract
import com.perfomax.dataviewer.presentation.home.HomeScreen
import com.perfomax.dataviewer.presentation.home.HomeViewModel

fun NavGraphBuilder.navigateToHome(){

    composable(route = HomeDestination.route) {
        val homeViewModel = hiltViewModel<HomeViewModel>()
        val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
        HomeScreen (
            uiState = homeUiState,
            onUpdateFeedsClick = {  }
        )
    }
}

object HomeDestination : NavigationDestination {
    override val route = "home"
}

object HomeTopLevelDestination : TopLevelDestination {
    override val route = HomeDestination.route
    override val iconId = R.drawable.ic_bottom_menu_home
    override val titleId = R.string.home
}
