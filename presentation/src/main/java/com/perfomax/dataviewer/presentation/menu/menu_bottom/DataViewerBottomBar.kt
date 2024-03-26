package com.perfomax.dataviewer.presentation.menu.menu_bottom

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.perfomax.dataviewer.navigation.TopLevelDestination

@Composable
fun DataViewerBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: String?,
    destinations: List<TopLevelDestination>,
    onNavigateToTopLevel: (topRoute: String) -> Unit
) {
    DataViewerNavBar(containerColor = MaterialTheme.colorScheme.onPrimary) {
        destinations.filter {
            it.route != "feeds" &&
            it.route != "projects"
        }.forEachIndexed { index, item ->
            DataViewerIconTab(
                modifier = modifier,
                colorNotSelected = MaterialTheme.colorScheme.onPrimary,
                colorSelected = MaterialTheme.colorScheme.primary,
                iconId = item.iconId,
                iconTitle = item.titleId,
                iconBadgeCounter = 0,
                enabled = false,
                selected = currentDestination == item.route,
                onClick = { onNavigateToTopLevel(item.route) },
            )
        }
    }

}