package com.perfomax.dataviewer.presentation.menu.menu_bottom

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.ui.R

@Composable
fun RowScope.DataViewerIconTab(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    colorNotSelected: Color,
    colorSelected: Color,
    alwaysShowLabel: Boolean = true,
    enabled: Boolean = true,
    iconId: Int,
    iconTitle: Int,
    iconBadgeCounter: Int?
) {

    NavigationBarItem(
        modifier = modifier,
        label = {
            Text(text = stringResource(id = iconTitle), fontSize = 13.sp)
        },
        icon = {
            DefaultBadgedBox(
                count = (iconBadgeCounter ?: stringResource(id = R.string.empty)).toString(),
                showBadgedBox = enabled,
                itemIconId = iconId,
                itemTitleId = iconTitle
            )
        },
        selected = selected,
        onClick = onClick,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = colorSelected,
            indicatorColor = colorNotSelected
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultBadgedBox(
    count: String? = null,
    itemIconId: Int,
    itemTitleId: Int,
    showBadgedBox: Boolean = true
) {

    if (showBadgedBox) {
        BadgedBox(badge = {
            Badge(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ) {
                Text(count?: EMPTY)
            }
        }) {
            Icon(painter = painterResource(id = itemIconId), contentDescription = stringResource(itemTitleId))
        }
    } else {
        Icon(painter = painterResource(id = itemIconId), contentDescription = stringResource(itemTitleId))
    }
}
