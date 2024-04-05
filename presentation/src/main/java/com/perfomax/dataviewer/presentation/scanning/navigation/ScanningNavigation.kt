package com.perfomax.dataviewer.presentation.scanning.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.navigation.NavigationDestination
import com.perfomax.dataviewer.navigation.TopLevelDestination
import com.perfomax.dataviewer.presentation.scanning.ScanningContract
import com.perfomax.dataviewer.presentation.scanning.ScanningScreen
import com.perfomax.dataviewer.presentation.scanning.ScanningViewModel
import com.perfomax.ui.R

fun NavGraphBuilder.scanning(){
        composable(
            route = ScanningDestination.route
        ) {
            val scanningViewModel = hiltViewModel<ScanningViewModel>()
            val scanningUiState by scanningViewModel.uiState.collectAsStateWithLifecycle()
            ScanningScreen(
                uiState = scanningUiState,
                onFeedUrlFieldChange = { feedUrl ->
                    scanningViewModel.intent(ScanningContract.Event.FeedUrlChangeEvent(feedUrl))
                },
                onSearchFeedElementFieldChange = { feedSearchedValue ->
                    scanningViewModel.intent(ScanningContract.Event.SearchFeedElementChangeEvent(feedSearchedValue))
                },
                onLoadFeedClick = { scanningViewModel.intent(ScanningContract.Event.LoadingFeedClickEvent) },
                onSearchFeedElementClick = { scanningViewModel.intent(ScanningContract.Event.SearchFeedElementClickEvent) }
            )
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