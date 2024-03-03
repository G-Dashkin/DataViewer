package com.example.dataviewer.presentation.scanning.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.dataviewer.R
import com.example.dataviewer.presentation.bottom_menu.NavigationDestination
import com.example.dataviewer.presentation.bottom_menu.TopLevelDestination
import com.example.dataviewer.presentation.scanning.ScanningScreen

fun NavGraphBuilder.navigateToScanning(){
    composable(route = ScanningDestination.route) {

//        val homeViewModel: HomeViewModel = viewModel(
//            factory = HomeViewModel.Factory(
//                getMenuSectionsUseCase = DiProvider.di.get(GetMenuSectionsUseCase::class),
//                getMenuItemsUseCase = DiProvider.di.get(GetMenuItemsUseCase::class),
//                addCartUseCase = DiProvider.di.get(AddCartUseCase::class)
//            )
//        )
//        val menuUiState by homeViewModel.uiStateMenu.collectAsStateWithLifecycle()
//
        ScanningScreen(
//            menuUiState = menuUiState,
//            onOfferClick = onOfferClick,
//            onItemSelected = onItemSelected,
//            onPickedSection = homeViewModel::updateMenu,
//            addToCartClick = homeViewModel::addToCart
        )

    }
}

object ScanningDestination : NavigationDestination {
    override val route = "scanning"
}

object ScanningTopLevelDestination : TopLevelDestination {
    override val route = ScanningDestination.route
    override val iconId = R.drawable.ic_bottom_menu_home
    override val titleId = R.string.scanning
}