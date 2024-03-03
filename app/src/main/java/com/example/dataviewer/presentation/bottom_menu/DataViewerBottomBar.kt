package com.example.dataviewer.presentation.bottom_menu

import androidx.compose.material3.MaterialTheme
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

    DataViewerNavBar(containerColor = MaterialTheme.colorScheme.onPrimary) {
        destinations.forEachIndexed { index, item ->
            DataViewerIconTab(
                modifier = modifier,
                colorNotSelected = MaterialTheme.colorScheme.onPrimary,
                colorSelected = MaterialTheme.colorScheme.primary,
                iconId = item.iconId,
                iconTitle = item.titleId,
//                iconBadgeCounter = if (item is CartTopLevelDestination && cartCount > zeroInt) cartCount else null,
//                enabled = item is CartTopLevelDestination && cartCount > zeroInt,
                iconBadgeCounter = 0,
                enabled = false,
                selected = currentDestination == item.route,
                onClick = { onNavigateToTopLevel(item.route) },
            )
        }
    }

}