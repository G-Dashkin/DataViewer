package com.example.dataviewer.presentation.feeds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.dataviewer.core.ui.theme.DataViewerTheme
import com.example.dataviewer.presentation.details.DetailsScreen

@Composable
fun FeedsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "FeedsScreen", fontSize = 22.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun FeedsScreenPreview() {
    DataViewerTheme {
        FeedsScreen()
    }
}