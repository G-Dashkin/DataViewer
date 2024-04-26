package com.perfomax.dataviewer.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.padding15

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

    val listOfUpdateTime = listOf("Каждый час", "Каждые 2 часа", "Каждые 3 часа", "Каждые 4 часа",
                                  "Каждые 5 часов", "Каждые 6 часов", "Каждые 7 часов", "Каждые 8 часов",
                                  "Каждые 9 часов", "Каждые 10 часов", "Каждые 11 часов", "Каждые 12 часов",
                                  "Каждые 13 часов", "Каждые 14 часов", "Каждые 15 часов", "Каждые 16 часов",
                                  "Каждые 17 часов", "Каждые 18 часов", "Каждые 19 часов", "Каждые 20 часов",
                                  "Каждый 21 час", "Каждые 22 часа", "Каждые 23 часа", "Каждые 24 часа")
    var selectedUpdateTime by remember { mutableStateOf(listOfUpdateTime[0]) }
    var isExpandedUpdateTime by remember { mutableStateOf(false) }


    val listOfUpdatePercent = listOf("Разница 10%", "Разница  20%", "Разница 30%",
                                     "Разница 40%", "Разница 50%", "Разница 60%",
                                     "Разница 70%", "Разница 80%", "Разница 90%")
    var selectedUpdatePercent by remember { mutableStateOf(listOfUpdatePercent[0]) }
    var isExpandedUpdatePercent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding15),
        horizontalAlignment = Alignment.Start
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()) {
            Switch(checked = uiState.isUpdateFeedsIntoBackground,
                onCheckedChange = onSwitchFeedUpdateIntoBackground)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Обновление фидов в фоне",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }
        //------------------------------------------------------------------------------------------
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()) {
            Switch(checked = uiState.isUpdateFeedsWithWIFI,
                onCheckedChange = onSwitchFeedUpdateWithWIFI)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Обновление только при wi-fi",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()) {
            Switch(checked = uiState.isNotificationWork,
                onCheckedChange = onSwitchNotification)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Включение уведосления при большой разнице в сравнении",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }

        //------------------------------------------------------------------------------------------
        Spacer(modifier = Modifier.heightIn(15.dp))
        Row(horizontalArrangement = Arrangement.Start) {
            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(0.51f),
                expanded = isExpandedUpdateTime,
                onExpandedChange = { isExpandedUpdateTime = !isExpandedUpdateTime }
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    value = selectedUpdateTime,
                    onValueChange = {  },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedUpdateTime) }
                )
                ExposedDropdownMenu(
                    expanded = isExpandedUpdateTime,
                    onDismissRequest = { isExpandedUpdateTime = false }) {
                    listOfUpdateTime.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text, style = MaterialTheme.typography.labelMedium) },
                            onClick = {
                                selectedUpdateTime = listOfUpdateTime[index]
                                isExpandedUpdateTime = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }

            }
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp),
                text = "Периодичность \nобновления",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }

        //------------------------------------------------------------------------------------------
        Spacer(modifier = Modifier.heightIn(15.dp))
        Row(horizontalArrangement = Arrangement.Start) {
            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(0.51f),
                expanded = isExpandedUpdatePercent,
                onExpandedChange = { isExpandedUpdatePercent = !isExpandedUpdatePercent }
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    value = selectedUpdatePercent,
                    onValueChange = {  },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedUpdatePercent) }
                )
                ExposedDropdownMenu(
                    expanded = isExpandedUpdatePercent,
                    onDismissRequest = { isExpandedUpdatePercent = false }) {
                    listOfUpdatePercent.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text, style = MaterialTheme.typography.labelMedium) },
                            onClick = {
                                selectedUpdatePercent = listOfUpdatePercent[index]
                                isExpandedUpdatePercent = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }

            }
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp),
                text = "Процент разнице \nпри обновлении",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
            text = "*уведомление при разнице элементов в фиде",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge)
        
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    DataViewerTheme {
//        SettingsScreen()
    }
}