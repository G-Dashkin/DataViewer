package com.example.dataviewer.presentation.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.dataviewer.R
import com.example.dataviewer.presentation.bottom_menu.NavigationDestination
import com.example.dataviewer.presentation.bottom_menu.TopLevelDestination
import com.example.dataviewer.presentation.scanning.navigation.ScanningDestination
import com.example.dataviewer.presentation.settings.SettingsScreen


fun NavGraphBuilder.navigateToSettings(){
    composable(route = SettingsDestination.route) {

//        val homeViewModel: HomeViewModel = viewModel(
//            factory = HomeViewModel.Factory(
//                getMenuSectionsUseCase = DiProvider.di.get(GetMenuSectionsUseCase::class),
//                getMenuItemsUseCase = DiProvider.di.get(GetMenuItemsUseCase::class),
//                addCartUseCase = DiProvider.di.get(AddCartUseCase::class)
//            )
//        )
//        val menuUiState by homeViewModel.uiStateMenu.collectAsStateWithLifecycle()
//
        SettingsScreen(
//            menuUiState = menuUiState,
//            onOfferClick = onOfferClick,
//            onItemSelected = onItemSelected,
//            onPickedSection = homeViewModel::updateMenu,
//            addToCartClick = homeViewModel::addToCart
        )

    }
}

object SettingsDestination : NavigationDestination {
    override val route = "settings"
}

object SettingsTopLevelDestination : TopLevelDestination {
    override val route = SettingsDestination.route
    override val iconId = R.drawable.ic_bottom_menu_home
    override val titleId = R.string.settings
}
