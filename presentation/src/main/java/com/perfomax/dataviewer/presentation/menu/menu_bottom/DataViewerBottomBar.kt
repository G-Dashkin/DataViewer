package com.perfomax.dataviewer.presentation.menu.menu_bottom

import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.perfomax.dataviewer.navigation.TopLevelDestination
import com.perfomax.dataviewer.ui.theme.border1

@Composable
fun DataViewerBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: String?,
    destinations: List<TopLevelDestination>,
    onNavigateToTopLevel: (topRoute: String) -> Unit
) {
    DataViewerNavBar(
        modifier = Modifier.border(border1, Color.Gray),
        containerColor = MaterialTheme.colorScheme.onPrimary) {
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