package com.perfomax.dataviewer.core.ui.widgets

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.perfomax.dataviewer.core.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.core.ui.theme.width40

@Composable
fun LoadingIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.width(width40),
        color = MaterialTheme.colorScheme.surfaceVariant,
        trackColor = MaterialTheme.colorScheme.secondary,
    )
}


@Preview(showBackground = true)
@Composable
fun LoadingIndicatorPreview() {
    DataViewerTheme {
        LoadingIndicator()
    }
}