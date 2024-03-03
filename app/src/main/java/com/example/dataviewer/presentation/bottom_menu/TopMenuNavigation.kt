package com.example.dataviewer.presentation.bottom_menu

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.dataviewer.R

private const val OFFSET_X = 50
private const val OFFSET_Y = (-60)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMenuNavigation(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    onNavigateToTopLevel: (topRoute: String) -> Unit
) {

    var dropDownMenuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            //--------------------------------------------------------------------------------------
            // Кнопка меню, три точки ...
            IconButton(onClick = { dropDownMenuExpanded = true }) {
                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "Options")
            }

            //--------------------------------------------------------------------------------------
            // Раскрывающееся меню при клике
            DropdownMenu(
                expanded = dropDownMenuExpanded,
                onDismissRequest = { dropDownMenuExpanded = false },
                offset = DpOffset(x = OFFSET_X.dp, y = OFFSET_Y.dp)
            ) {
                //--------------------------------------------------------------------------------------
                // Сами элементы меню перебираемые в цикле
                destinations.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = item.titleId)) },
                        onClick = {
                            dropDownMenuExpanded = false
                            onNavigateToTopLevel(item.route)
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
    TopMenuNavigation(
        destinations = listOf(),
        onNavigateToTopLevel = {},
    )
}