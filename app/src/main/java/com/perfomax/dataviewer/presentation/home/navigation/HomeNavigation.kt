package com.perfomax.dataviewer.presentation.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perfomax.dataviewer.R
import com.perfomax.dataviewer.presentation.menu.NavigationDestination
import com.perfomax.dataviewer.presentation.menu.TopLevelDestination
import com.perfomax.dataviewer.presentation.home.HomeScreen

fun NavGraphBuilder.navigateToHome(
//    onItemSelected: (String) -> Unit,
//    onOfferClick: () -> Unit
){
    composable(route = HomeDestination.route) {

//        val homeViewModel: HomeViewModel = viewModel(
////            factory = HomeViewModel.Factory(
////                getMenuSectionsUseCase = DiProvider.di.get(GetMenuSectionsUseCase::class),
////                getMenuItemsUseCase = DiProvider.di.get(GetMenuItemsUseCase::class),
////                addCartUseCase = DiProvider.di.get(AddCartUseCase::class)
////            )
//        )
//        val menuUiState by homeViewModel.uiStateMenu.collectAsStateWithLifecycle()

        HomeScreen(
//            menuUiState = menuUiState,
//            onOfferClick = onOfferClick,
//            onItemSelected = onItemSelected,
//            onPickedSection = homeViewModel::updateMenu,
//            addToCartClick = homeViewModel::addToCart
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
