package com.perfomax.dataviewer.presentation.feeds

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.fillMaxWidth07
import com.perfomax.dataviewer.ui.theme.height15
import com.perfomax.dataviewer.ui.theme.height2
import com.perfomax.dataviewer.ui.theme.height50
import com.perfomax.dataviewer.ui.theme.padding15
import com.perfomax.dataviewer.ui.theme.shape8
import com.perfomax.dataviewer.ui.theme.zeroVal
import com.perfomax.dataviewer.ui.widgets.DefaultDialogView
import com.perfomax.dataviewer.ui.widgets.FeedItem
import com.perfomax.dataviewer.ui.widgets.FeedsDialogView
import com.perfomax.dataviewer.ui.widgets.FeedsScreenFormTextField
import com.perfomax.dataviewer.ui.widgets.LoadingIndicator
import com.perfomax.ui.R

@Composable
fun FeedsScreen(
    uiState: FeedsContract.State,
    onFeedUrlFieldChange: (String) -> Unit,
    onAddFeedClick: () -> Unit,
    onOpenDialogSelectedFeedElementClick: () -> Unit,
    onCloseDialogSelectedFeedElement: () -> Unit,
    onFeedNameFieldChange: (String) -> Unit,
    onSelectFeedElement: (String) -> Unit,
    onSelectRemovedFeedNameClick:(String) -> Unit,
    onCloseDialogRemoveFeedClick:() -> Unit,
    onRemoveFeedClick:() -> Unit,
    onAddNewFeed:() -> Unit,
    updateProject:() -> Unit,
    onSwitchToFeedsListClick:() -> Unit
) {

    updateProject.invoke()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding15),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = stringResource(id = R.string.load_new_feed),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(height15))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            FeedsScreenFormTextField(
                modifier = Modifier
                    .fillMaxWidth(fillMaxWidth07)
                    .height(height50)
                    .defaultMinSize(minHeight = height50)
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(zeroVal),
                text = uiState.feedUrl,
                labelText = stringResource(id = R.string.feed_url),
                isError = uiState.feedUrlError,
                onChange = onFeedUrlFieldChange
            )
            Spacer(modifier = Modifier.width(10.dp))

            if (uiState.isFeedsList){
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(height50)
                    .defaultMinSize(minHeight = height50),
                    shape = RoundedCornerShape(shape8),
                    contentPadding = PaddingValues(zeroVal),
                    onClick = onAddFeedClick
                ) {
                    Text(text = stringResource(id = R.string.load),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineLarge)
                }
            } else {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(height50)
                    .defaultMinSize(minHeight = height50),
                    shape = RoundedCornerShape(shape8),
                    contentPadding = PaddingValues(zeroVal),
                    onClick = onSwitchToFeedsListClick
                ) {
                    Text(text = stringResource(id = R.string.cancel),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineLarge)
                }
            }


        }
        Spacer(modifier = Modifier.height(5.dp))
        Column(modifier = Modifier.fillMaxSize()) {
            if (uiState.isFeedsList){
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.feedsList) { element ->
                        FeedItem(
                            feedName = element.feedName,
                            onRemoveBottom = true,
                            onRemove = onSelectRemovedFeedNameClick,
                        )
                    }
                }
            } else {
                if (uiState.loadedFeed.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator()
                    }
                }
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.loadedFeed) { element ->
                        Text(
                            modifier = Modifier
                                .background(Color.Gray)
                                .clickable {
                                    onSelectFeedElement.invoke(element)
                                    onOpenDialogSelectedFeedElementClick.invoke()
                                },
                            text = element,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(height2))
                    }
                }
            }
        }
    }

    FeedsDialogView(
        title = stringResource(id = R.string.adding_feed),
        feedNameValue = uiState.feedName,
        onFeedNameFieldChangeValue = onFeedNameFieldChange,
        feedElementNameValue = uiState.selectedFeedElement,
        onFeedElementFieldChangeValue = onSelectFeedElement,
        hasDateElement = true,
        dateElement = "<yml_catalog date=\"2024-03-21 20:52\">",
        useDateElement = {},
        openDialog = uiState.openDialogSelectedFeedElement,
        onCancel = onCloseDialogSelectedFeedElement,
        onConfirm = onAddNewFeed
    )

    DefaultDialogView(
        textValue = uiState.feedName,
        title = stringResource(id = R.string.delete_feed) + uiState.removedFeed,
        openDialog = uiState.openDialogRemoveFeed,
        onCancel = onCloseDialogRemoveFeedClick,
        onConfirm = onRemoveFeedClick
    )

}

@Preview(showBackground = true)
@Composable
fun FeedsScreenPreview() {
    val feedsViewModel: FeedsViewModel = viewModel()
    val feedsUiState by feedsViewModel.uiState.collectAsStateWithLifecycle()
    val feedsEffects by feedsViewModel.effect.collectAsStateWithLifecycle()
    DataViewerTheme {
//        FeedsScreen(
//            uiState = feedsUiState,
//            onFeedFieldChange = { text ->
//                feedsViewModel.intent(
//                    FeedsContract.Event.FeedUrlChangeEvent(
//                        text
//                    )
//                )
//            },
//            onAddFeedClick = { feedsViewModel.intent(FeedsContract.Event.AddFeedClickEvent) },
//            onSelectFeedElement = { }
//
//        )
    }
}