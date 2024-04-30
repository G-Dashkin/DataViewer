package com.perfomax.dataviewer.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.perfomax.dataviewer.domain.utils.getFeedElementValue
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.cornerShape8
import com.perfomax.dataviewer.ui.theme.height50
import com.perfomax.dataviewer.ui.theme.padding10
import com.perfomax.dataviewer.ui.theme.padding15
import com.perfomax.dataviewer.ui.theme.zeroVal
import com.perfomax.dataviewer.ui.widgets.DialogViewDefault
import com.perfomax.dataviewer.ui.widgets.ItemFeedHome
import com.perfomax.dataviewer.ui.widgets.DialogViewHomeScreenFeed
import com.perfomax.dataviewer.ui.widgets.LoadingIndicator
import com.perfomax.ui.R

@Composable
fun HomeScreen(
    uiState: HomeContract.State,
    updateFeedsList:() -> Unit,
    updateBackgroundUpdate:() -> Unit,
    onClickFeedElement:(String) -> Unit,
    onFindSelectedElement:(String) -> Unit,
    onClickUpdateFeedsList:() -> Unit,
    onClickUpdateFeed:() -> Unit,
    onCloseDialogClick:() -> Unit,
    onCloseDialogIsConnected: () -> Unit
) {

    updateFeedsList.invoke()
    updateBackgroundUpdate.invoke()

    Column(
        modifier = Modifier.fillMaxSize()
                           .padding(padding15),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(modifier = Modifier.fillMaxWidth()
                                  .height(height50)
                                  .defaultMinSize(minHeight = height50),
            enabled = !uiState.isUpdatingFeedList,
            shape = RoundedCornerShape(cornerShape8),
            contentPadding = PaddingValues(zeroVal),
            onClick = onClickUpdateFeedsList
        ) {
            Text(text = stringResource(id = R.string.feeds_update),
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleLarge)
            }
        Spacer(modifier = Modifier.height(padding10))
        if (uiState.isUpdatingFeedList) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingIndicator()
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(uiState.feedsList) { element ->
                ItemFeedHome(
                    feedName = element.feedName,
                    countElements = element.feedElementCount,
                    updateTime = element.feedUpdateTime.getFeedElementValue(),
                    loadTime = element.feedLoadTime,
                    onClickFeedElement = onClickFeedElement
                )
            }
        }

        // DialogView for feeds list
        DialogViewHomeScreenFeed(
            feedTitle = uiState.selectedFeedName,
            feedUrl = uiState.selectedFeedUrl,
            onFindElementsInFeed = onFindSelectedElement,
            openDialog = uiState.openDialogHomeScreenFeed,
            onUpdateFeed = onClickUpdateFeed,
            onClose = onCloseDialogClick
        )

        // DialogView for connection error alert
        DialogViewDefault(
            title = stringResource(id = R.string.internet_error),
            openDialog = uiState.openDialogIsConnected,
            onlyCancel = true,
            onCancel = onCloseDialogIsConnected
        )

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DataViewerTheme {
        HomeScreen (
            uiState = HomeContract.State.initial(),
            updateFeedsList = {},
            onClickUpdateFeedsList = {},
            onClickFeedElement = {},
            onFindSelectedElement = {},
            onClickUpdateFeed = { },
            onCloseDialogClick= { },
            updateBackgroundUpdate = {},
            onCloseDialogIsConnected = {}
        )
    }
}