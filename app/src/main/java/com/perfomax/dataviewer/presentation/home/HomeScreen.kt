package com.perfomax.dataviewer.presentation.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.perfomax.dataviewer.core.ui.theme.DataViewerTheme

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize().border(1.dp, Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "HomeScreen", fontSize = 22.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DataViewerTheme {
        HomeScreen()
    }
}