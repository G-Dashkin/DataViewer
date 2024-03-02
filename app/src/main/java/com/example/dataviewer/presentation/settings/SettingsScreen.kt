package com.example.dataviewer.presentation.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.dataviewer.core.ui.theme.DataViewerTheme
import com.example.dataviewer.presentation.scanning.ScanningScreen

@Composable
fun SettingsScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "SettingsScreen", fontSize = 22.sp)
    }

}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    DataViewerTheme {
        SettingsScreen()
    }
}