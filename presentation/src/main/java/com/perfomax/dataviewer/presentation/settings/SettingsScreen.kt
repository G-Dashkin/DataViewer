package com.perfomax.dataviewer.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.padding15

@Composable
fun SettingsScreen(
    uiState: SettingsContract.State,
    onSwitchFeedUpdateIntoBackground:(Boolean) -> Unit,
    onSetUpdatePeriod:(String) -> Unit,
    onSwitchFeedUpdateWithWIFI:(Boolean) -> Unit,
    onSetPercentForAlert:(String) -> Unit,
    onSwitchNotification:(Boolean) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize().padding(padding15),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(60.dp).fillMaxWidth()) {
            Switch(checked = uiState.isUpdateFeedsIntoBackground,
                onCheckedChange = onSwitchFeedUpdateIntoBackground)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Обновление фидов в фоне",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(60.dp).fillMaxWidth()) {
            TextField(
                modifier = Modifier.width(60.dp),
                value = uiState.updatePeriod,
                onValueChange = onSetUpdatePeriod,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Периодичность обновления",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(60.dp).fillMaxWidth()) {
            Switch(checked = uiState.isUpdateFeedsWithWIFI,
                onCheckedChange = onSwitchFeedUpdateWithWIFI)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Обновление только при wi-fi",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(60.dp).fillMaxWidth()) {
            TextField(
                modifier = Modifier.width(60.dp),
                value = uiState.comparisonPercent,
                onValueChange = onSetPercentForAlert,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Процент сравнения при обновлении",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(60.dp).fillMaxWidth()) {
            Switch(checked = uiState.isNotificationWork,
                onCheckedChange = onSwitchNotification)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Включение нотификации при большой разнице в сравнении",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge)
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    DataViewerTheme {
//        SettingsScreen()
    }
}