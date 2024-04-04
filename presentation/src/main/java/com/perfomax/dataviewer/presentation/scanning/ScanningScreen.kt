package com.perfomax.dataviewer.presentation.scanning


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.fillMaxWidth07
import com.perfomax.dataviewer.ui.theme.height2
import com.perfomax.dataviewer.ui.theme.height50
import com.perfomax.dataviewer.ui.theme.padding15
import com.perfomax.dataviewer.ui.theme.shape8
import com.perfomax.dataviewer.ui.theme.zeroVal
import com.perfomax.dataviewer.ui.widgets.FeedItem
import com.perfomax.dataviewer.ui.widgets.FeedsScreenFormTextField
import com.perfomax.ui.R

@Composable
fun ScanningScreen(
    uiState: ScanningContract.State,
    onFeedUrlFieldChange: (String) -> Unit,
    onSearchFeedElementFieldChange: (String) -> Unit,
    onLoadFeedClick: () -> Unit,
    onSearchFeedElementClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding15),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            FeedsScreenFormTextField(
                modifier = Modifier
                    .fillMaxWidth(fillMaxWidth07)
                    .height(70.dp)
                    .defaultMinSize(minHeight = height50)
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(zeroVal),
                text = uiState.feedUrl,
                labelText = stringResource(id = R.string.feed_url),
                isError = false,
                onChange = onFeedUrlFieldChange
            )
            Spacer(modifier = Modifier.width(width = 5.dp))
            Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .defaultMinSize(minHeight = height50),
//                    enabled = !uiState.isCountingFeedElements,
                    shape = RoundedCornerShape(shape8),
                    contentPadding = PaddingValues(zeroVal),
                    onClick = onLoadFeedClick
                ) {
                    Text(text = stringResource(id = R.string.load),
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.titleMedium)
                }
        }

        if (uiState.isFeedScanningResponse) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                FeedsScreenFormTextField(
                    modifier = Modifier
                        .fillMaxWidth(fillMaxWidth07)
                        .height(70.dp)
                        .defaultMinSize(minHeight = height50)
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(zeroVal),
                    text = uiState.feedSearchValue,
                    labelText = stringResource(id = R.string.feed_searching),
                    isError = false,
                    onChange = onSearchFeedElementFieldChange
                )
                Spacer(modifier = Modifier.width(width = 5.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .defaultMinSize(minHeight = height50),
//                    enabled = !uiState.isCountingFeedElements,
                    shape = RoundedCornerShape(shape8),
                    contentPadding = PaddingValues(zeroVal),
                    onClick = onSearchFeedElementClick
                ) {
                    Text(
                        text = stringResource(id = R.string.search),
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.loadedFeed) { element ->
                    Text(
                        modifier = Modifier.background(Color.Gray),
                        text = element,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(height2))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScanningScreenPreview() {
    DataViewerTheme {
//        ScanningScreen()
    }
}