package com.perfomax.dataviewer.presentation.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.R
import com.perfomax.dataviewer.presentation.menu.NavigationDestination
import com.perfomax.dataviewer.presentation.menu.TopLevelDestination
import com.perfomax.dataviewer.presentation.settings.SettingsScreen


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
