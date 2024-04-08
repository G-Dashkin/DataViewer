package com.perfomax.dataviewer.presentation.settings

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.perfomax.dataviewer.domain.utils.getFeedElementValue
import com.perfomax.dataviewer.presentation.home.HomeContract
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.height50
import com.perfomax.dataviewer.ui.theme.padding15
import com.perfomax.dataviewer.ui.theme.shape8
import com.perfomax.dataviewer.ui.theme.zeroVal
import com.perfomax.dataviewer.ui.widgets.FeedItem
import com.perfomax.dataviewer.ui.widgets.HomeScreenFeedDialogView
import com.perfomax.dataviewer.ui.widgets.LoadingIndicator
import com.perfomax.ui.R

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