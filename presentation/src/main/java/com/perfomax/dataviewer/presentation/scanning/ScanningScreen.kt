package com.perfomax.dataviewer.presentation.scanning


import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.border1
import com.perfomax.dataviewer.ui.theme.cornerShape8
import com.perfomax.dataviewer.ui.theme.fillMaxWidth07
import com.perfomax.dataviewer.ui.theme.height2
import com.perfomax.dataviewer.ui.theme.height40
import com.perfomax.dataviewer.ui.theme.height50
import com.perfomax.dataviewer.ui.theme.height70
import com.perfomax.dataviewer.ui.theme.padding15
import com.perfomax.dataviewer.ui.theme.padding5
import com.perfomax.dataviewer.ui.theme.width5
import com.perfomax.dataviewer.ui.theme.zeroVal
import com.perfomax.dataviewer.ui.widgets.DialogViewDefault
import com.perfomax.dataviewer.ui.widgets.FormTextFieldDefault
import com.perfomax.dataviewer.ui.widgets.LoadingIndicator
import com.perfomax.ui.R

@Composable
fun ScanningScreen(
    uiState: ScanningContract.State,
    onFeedUrlFieldChange: (String) -> Unit,
    onSearchFeedElementFieldChange: (String) -> Unit,
    onLoadFeedClick: () -> Unit,
    onSearchFeedElementClick: () -> Unit,
    onCloseDialogIsConnected: () -> Unit,
    onCloseDialogIsNotFind: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
                           .padding(start = padding15, top = padding15, end = padding15),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            FormTextFieldDefault(
                modifier = Modifier.fillMaxWidth(fillMaxWidth07)
                                   .defaultMinSize(minHeight = height50)
                                   .background(color = MaterialTheme.colorScheme.background)
                                   .padding(zeroVal),
                text = uiState.feedUrl,
                labelText = stringResource(id = R.string.feed_url),
                isError = false,
                onChange = onFeedUrlFieldChange
            )
            Spacer(modifier = Modifier.width(width = width5))
            Button(
                modifier = Modifier.fillMaxWidth()
                                   .height(height50)
                                   .requiredHeight(height40),
                    enabled = !uiState.isScanningFeed,
                    shape = RoundedCornerShape(cornerShape8),
                    contentPadding = PaddingValues(zeroVal),
                    onClick = onLoadFeedClick
                ) {
                    Text(text = stringResource(id = R.string.load),
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.titleMedium)
                }
            }

        if (uiState.isScanningFeed) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingIndicator()
                Text(text = stringResource(id = R.string.scanning))
            }
        }

        if (uiState.isFeedScanningResponse) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                FormTextFieldDefault(
                    modifier = Modifier.fillMaxWidth(fillMaxWidth07)
                                       .height(height70)
                                       .defaultMinSize(minHeight = height50)
                                       .background(color = MaterialTheme.colorScheme.background)
                                       .padding(zeroVal),
                    text = uiState.feedSearchValue,
                    labelText = stringResource(id = R.string.feed_searching),
                    isError = false,
                    onChange = onSearchFeedElementFieldChange
                )
                Spacer(modifier = Modifier.width(width = width5))
                Button(
                    modifier = Modifier.fillMaxWidth()
                                       .height(height70)
                                       .requiredHeight(height40),
                    shape = RoundedCornerShape(cornerShape8),
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
            LazyColumn(
                state = uiState.listState,
                modifier = Modifier.fillMaxSize()
                                   .border(border1, Color.Gray)
                                   .padding(padding5)
            ) {
                itemsIndexed(items = uiState.loadedFeed) { index, element ->
                    if (element.contains(uiState.feedSearchValue) && uiState.feedSearchValue.isNotEmpty()) {
                        Text(modifier = Modifier.background(Color.Gray), text = element, color = Color.White)
                    } else { Text(text = element, color = Color.Black) }
                    Spacer(modifier = Modifier.height(height2))
                }
            }
        }

        // DialogView for connection error alert
        DialogViewDefault(
            title = stringResource(id = R.string.internet_error),
            openDialog = uiState.openDialogIsConnected,
            onlyCancel = true,
            onCancel = onCloseDialogIsConnected
        )

        // DialogView for not Find
        DialogViewDefault(
            title = stringResource(id = R.string.not_find),
            openDialog = uiState.openDialogIsNotFind,
            onlyCancel = true,
            onCancel = onCloseDialogIsNotFind
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ScanningScreenPreview() {
    DataViewerTheme {
        ScanningScreen(
            uiState =  ScanningContract.State.initial(),
            onFeedUrlFieldChange = { },
            onSearchFeedElementFieldChange = {},
            onLoadFeedClick = {  },
            onSearchFeedElementClick = {  },
            onCloseDialogIsConnected = {  },
            onCloseDialogIsNotFind = {  }
        )
    }
}