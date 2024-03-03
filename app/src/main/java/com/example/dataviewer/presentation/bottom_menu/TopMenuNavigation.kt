package com.example.dataviewer.presentation.bottom_menu

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
    onHomeClick: () -> Unit = {},
    onProjectsClick: () -> Unit = {},
    onFeedsClick: () -> Unit = {}
) {

    var dropDownMenuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge
            )
        },

        actions = {

            IconButton(onClick = {
                dropDownMenuExpanded = true
            }) {
                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "Options")
            }

            DropdownMenu(
                expanded = dropDownMenuExpanded,
                onDismissRequest = {
                    dropDownMenuExpanded = false
                },
                offset = DpOffset(x = OFFSET_X.dp, y = OFFSET_Y.dp)
            ) {


                DropdownMenuItem(onClick = {
                    dropDownMenuExpanded = false
                    onHomeClick.invoke()
                }, text = {
                    Text(text = stringResource(id = R.string.home))
                })

                DropdownMenuItem(onClick = {
                    dropDownMenuExpanded = false
                    onProjectsClick.invoke()
                }, text = {
                    Text(text = stringResource(id = R.string.projects))
                })

                DropdownMenuItem(onClick = {
                    dropDownMenuExpanded = false
                    onFeedsClick.invoke()
                }, text = {
                    Text(text = stringResource(id = R.string.feeds))
                })

            }
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    TopMenuNavigation()
}