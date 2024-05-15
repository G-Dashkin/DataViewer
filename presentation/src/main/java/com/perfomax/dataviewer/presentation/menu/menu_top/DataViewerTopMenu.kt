package com.perfomax.dataviewer.presentation.menu.menu_top

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.perfomax.dataviewer.navigation.TopLevelDestination
import com.perfomax.dataviewer.presentation.auth.navigation.LoginDestination
import com.perfomax.dataviewer.presentation.scanning.navigation.ScanningDestination
import com.perfomax.dataviewer.ui.theme.border1
import com.perfomax.dataviewer.ui.theme.height2
import com.perfomax.dataviewer.ui.theme.padding15
import com.perfomax.dataviewer.ui.theme.width250
import com.perfomax.ui.R

private const val OFFSET_X = 50
private const val OFFSET_Y = (-60)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataViewerTopMenu(
    modifier: Modifier = Modifier,
    titleTopMenu: String,
    authUser: String,
    destinations: List<TopLevelDestination>,
    onNavigateToTopLevel: (topRoute: String) -> Unit,
    onAuthentication: () -> Unit,
    updateMainProject:() -> Unit
) {

    var dropDownMenuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        modifier = Modifier.border(border1, Color.Gray),
        title = { Text(text = stringResource(id = R.string.project ) + " $titleTopMenu") },
        actions = {
            IconButton(onClick = { dropDownMenuExpanded = true }) {
                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "Options")
            }
            DropdownMenu(
                expanded = dropDownMenuExpanded,
                onDismissRequest = { dropDownMenuExpanded = false },
                offset = DpOffset(x = OFFSET_X.dp, y = OFFSET_Y.dp)
            ) {
                if (authUser.isNotEmpty()) {
                    Text(modifier = modifier.padding(start = padding15, end = padding15),
                         text = "${stringResource(id = R.string.user)} $authUser",
                         color = MaterialTheme.colorScheme.onBackground,
                         style = MaterialTheme.typography.titleSmall)
                    Divider (modifier = Modifier.fillMaxWidth()
                                                .height(height2),
                             color = Color.Black)
                }
                destinations.filter {
                    !it.route.contains(ScanningDestination.route)
                }.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        modifier = modifier.width(width250),
                        text = { Text(text = stringResource(id = item.titleId)) },
                        onClick = {
                            dropDownMenuExpanded = false
                            if (item.route != LoginDestination.route) {
                                onNavigateToTopLevel(item.route)
                                updateMainProject.invoke()
                            } else {
                                onAuthentication.invoke()
                            }
                        }
                    )
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    DataViewerTopMenu(
        destinations = listOf(),
        titleTopMenu = "",
        authUser = "",
        onNavigateToTopLevel = {},
        onAuthentication =  {},
        updateMainProject = { }
    )
}