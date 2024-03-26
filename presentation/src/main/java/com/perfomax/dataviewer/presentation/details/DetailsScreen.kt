package com.perfomax.dataviewer.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.ui.R

@Composable
fun DetailsScreen() {

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.details_screen),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge)
    }

}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DataViewerTheme {
        DetailsScreen()
    }
}