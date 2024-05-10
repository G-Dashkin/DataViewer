package com.perfomax.dataviewer.presentation.settings

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.height15
import com.perfomax.dataviewer.ui.theme.height60
import com.perfomax.dataviewer.ui.theme.maxWidth051
import com.perfomax.dataviewer.ui.theme.padding10
import com.perfomax.dataviewer.ui.theme.padding15
import com.perfomax.dataviewer.ui.theme.padding5
import com.perfomax.dataviewer.ui.theme.width5
import com.perfomax.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    uiState: SettingsContract.State,
    onSwitchFeedUpdateIntoBackground:(Boolean) -> Unit,
    onSetUpdatePeriod:(String) -> Unit,
    onSwitchFeedUpdateWithWIFI:(Boolean) -> Unit,
    onSetPercentForAlert:(String) -> Unit,
    onSwitchNotification:(Boolean) -> Unit
) {

    var isExpandedUpdateTime by remember { mutableStateOf(false) }
    var isExpandedUpdatePercent by remember { mutableStateOf(false) }

//    var selectedUpdateTime by remember { mutableStateOf(uiState.listOfUpdateTime[0]) }

    Column(modifier = Modifier.fillMaxSize()
                              .padding(padding15),
           horizontalAlignment = Alignment.Start
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                               .height(height60)
        ) {
            Switch(checked = uiState.isUpdateFeedsIntoBackground,
                onCheckedChange = onSwitchFeedUpdateIntoBackground)
            Spacer(modifier = Modifier.width(width5))
            Text(text =  stringResource(id = R.string.update_into_background),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                               .height(height60)
        ) {
            Switch(checked = uiState.isUpdateFeedsWithWIFI,
                onCheckedChange = onSwitchFeedUpdateWithWIFI)
            Spacer(modifier = Modifier.width(width5))
            Text(text = stringResource(id = R.string.wi_fi_update),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                               .height(height60)
        ) {
            Switch(checked = uiState.isNotificationWork,
                onCheckedChange = onSwitchNotification)
            Spacer(modifier = Modifier.width(width5))
            Text(text = stringResource(id = R.string.alert_on),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.heightIn(height15))
        Row(horizontalArrangement = Arrangement.Start) {
            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(maxWidth051),
                expanded = isExpandedUpdateTime,
                onExpandedChange = { isExpandedUpdateTime = !isExpandedUpdateTime }
            ) {
//                TextField(
//                    modifier = Modifier.menuAnchor(),
//                    value = selectedUpdateTime,
//                    onValueChange = {  },
//                    readOnly = true,
//                    trailingIcon = {
//                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedUpdateTime)
//                    }
//                )
                ExposedDropdownMenu(
                    expanded = isExpandedUpdateTime,
                    onDismissRequest = { isExpandedUpdateTime = false }) {
                    uiState.listOfUpdateTime.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text, style = MaterialTheme.typography.labelMedium) },
                            onClick = {
//                                selectedUpdateTime = uiState.listOfUpdateTime[index]
//                                isExpandedUpdateTime = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
            Text(modifier = Modifier.fillMaxWidth()
                                    .padding(start = padding5),
                text = stringResource(id = R.string.update_periodic),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.heightIn(height15))
        Row(horizontalArrangement = Arrangement.Start) {
            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(maxWidth051),
                expanded = isExpandedUpdatePercent,
                onExpandedChange = { isExpandedUpdatePercent = !isExpandedUpdatePercent }
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    value = uiState.comparisonAlertPercentName,
                    onValueChange = {  },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedUpdatePercent) }
                )
                ExposedDropdownMenu(
                    expanded = isExpandedUpdatePercent,
                    onDismissRequest = { isExpandedUpdatePercent = false }) {
                    uiState.listOfAlertPercent.keys.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = {
                                Text(text = uiState.listOfAlertPercent.getOrDefault(text, EMPTY),
                                     style = MaterialTheme.typography.labelMedium)
                            },
                            onClick = {
                                isExpandedUpdatePercent = false
                                onSetPercentForAlert.invoke(uiState.listOfAlertPercent.getOrDefault(text, EMPTY))
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }

            }
            Text(modifier = Modifier.fillMaxWidth()
                                    .padding(start = padding5),
                text = stringResource(id = R.string.different_percent),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }
        Text(modifier = Modifier.fillMaxWidth()
                                .padding(start = padding10),
            text = stringResource(id = R.string.different_percent_desc),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    DataViewerTheme {
        SettingsScreen(
            uiState = SettingsContract.State.initial(),
            onSetPercentForAlert = {},
            onSwitchFeedUpdateIntoBackground = {},
            onSetUpdatePeriod = {},
            onSwitchFeedUpdateWithWIFI = {},
            onSwitchNotification = {}
        )
    }
}