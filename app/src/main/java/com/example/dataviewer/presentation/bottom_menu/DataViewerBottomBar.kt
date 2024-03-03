package com.example.dataviewer.presentation.bottom_menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DataViewerBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: String?,
    destinations: List<TopLevelDestination>,
    onNavigateToTopLevel: (topRoute: String) -> Unit
) {

//    val cartRepository = DiProvider.di.get(CartRepository::class)
//    val cartState by rememberUpdatedState  { cartRepository }
//    val cartCount = cartState.invoke().size()

}