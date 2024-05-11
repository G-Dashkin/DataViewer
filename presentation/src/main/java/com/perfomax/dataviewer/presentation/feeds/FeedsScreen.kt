package com.perfomax.dataviewer.presentation.feeds

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.requiredHeight
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.border1
import com.perfomax.dataviewer.ui.theme.cornerShape8
import com.perfomax.dataviewer.ui.theme.fillMaxWidth07
import com.perfomax.dataviewer.ui.theme.height2
import com.perfomax.dataviewer.ui.theme.height40
import com.perfomax.dataviewer.ui.theme.height5
import com.perfomax.dataviewer.ui.theme.height50
import com.perfomax.dataviewer.ui.theme.padding10
import com.perfomax.dataviewer.ui.theme.padding15
import com.perfomax.dataviewer.ui.theme.padding5
import com.perfomax.dataviewer.ui.theme.width10
import com.perfomax.dataviewer.ui.theme.zeroVal
import com.perfomax.dataviewer.ui.widgets.DialogViewAddFeed
import com.perfomax.dataviewer.ui.widgets.DialogViewChangeFeed
import com.perfomax.dataviewer.ui.widgets.DialogViewDefault
import com.perfomax.dataviewer.ui.widgets.FormTextFieldDefault
import com.perfomax.dataviewer.ui.widgets.ItemFeedSettings
import com.perfomax.dataviewer.ui.widgets.LoadingIndicator
import com.perfomax.ui.R

@Composable
fun FeedsScreen(
    uiState: FeedsContract.State,
    onFeedUrlFieldChange: (String) -> Unit,
    onAddFeedClick: () -> Unit,
    onOpenDialogSelectedFeedElementClick: () -> Unit,
    onOpenChangeFeedDialog: (String) -> Unit,
    onCloseDialogChangeFeed: () -> Unit,
    onFeedTitleFieldChangeValue: (String) -> Unit,
    onFeedUrlFieldChangeValue: (String) -> Unit,
    onFeedCountElementFieldChangeValue: (String) -> Unit,
    onCloseDialogSelectedFeedElement: () -> Unit,
    onFeedNameFieldChange: (String) -> Unit,
    onSelectFeedElement: (String) -> Unit,
    onSelectFeedDateElement: (String) -> Unit,
    onSelectRemovedFeedNameClick:(String) -> Unit,
    onCloseDialogRemoveFeedClick:() -> Unit,
    onRemoveFeedClick:() -> Unit,
    onAddNewFeed:() -> Unit,
    updateProject:() -> Unit,
    onSwitchToFeedsListClick:() -> Unit,
    onCloseDialogFeedUrlError:() -> Unit,
    onCloseDialogIsConnected: () -> Unit,
    onCloseDialogNotProject: () -> Unit,
    selectDateElement: () -> Unit,
    onSaveChanges:() -> Unit
) {

    updateProject.invoke()

    Column(
        modifier = Modifier.fillMaxSize()
                           .padding(start = padding15, end = padding15),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = stringResource(id = R.string.load_new_feed),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            FormTextFieldDefault(
                modifier = Modifier.fillMaxWidth(fillMaxWidth07)
                                   .height(height50)
                                   .defaultMinSize(minHeight = height50)
                                   .background(color = MaterialTheme.colorScheme.background)
                                   .padding(zeroVal),
                text = uiState.feedUrl,
                labelText = stringResource(id = R.string.feed_url),
                isError = uiState.feedUrlError,
                onChange = onFeedUrlFieldChange
            )
            Spacer(modifier = Modifier.width(width10))
            if (uiState.isFeedsList){
                Button(modifier = Modifier.fillMaxWidth()
                                          .height(height50)
                                          .requiredHeight(height40),
                    enabled = !uiState.isCountingFeedElements,
                    shape = RoundedCornerShape(cornerShape8),
                    contentPadding = PaddingValues(zeroVal),
                    onClick = onAddFeedClick
                ) {
                    Text(text = stringResource(id = R.string.load),
                         color = MaterialTheme.colorScheme.onSecondary,
                         style = MaterialTheme.typography.titleMedium)
                }
            } else {
                Button(modifier = Modifier.fillMaxWidth()
                                          .height(height50)
                                          .requiredHeight(height40),
                    shape = RoundedCornerShape(cornerShape8),
                    contentPadding = PaddingValues(zeroVal),
                    onClick = onSwitchToFeedsListClick
                ) {
                    Text(text = stringResource(id = R.string.cancel),
                         color = MaterialTheme.colorScheme.onSecondary,
                         style = MaterialTheme.typography.titleMedium)
                }
            }
        }
        if (uiState.isCountingFeedElements) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingIndicator()
                Text(text = stringResource(id = R.string.count_feed_elements))
            }
        }
        Column(modifier = Modifier.fillMaxSize()) {
            if (uiState.isFeedsList){
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.feedsList) { element ->
                        Spacer(modifier = Modifier.height(height5))
                        ItemFeedSettings(
                            feedName = element.feedName,
                            onRemoveBottom = true,
                            onRemove = onSelectRemovedFeedNameClick,
                            onOpenChangeFeedDialog = onOpenChangeFeedDialog
                        )
                    }
                }
            } else {
                if (uiState.loadedFeed.isEmpty() && !uiState.feedUrlError) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LoadingIndicator()
                        Text(text = stringResource(id = R.string.loading_feed))
                    }
                }
                if(uiState.feedUrlError) {
                    Box(modifier = Modifier.fillMaxWidth()
                                           .border(border1, Color.Gray)
                                           .padding(padding10)
                        ) {
                        Text(text = uiState.feedUrlErrorMessage,
                             color = Color.Red,
                             style = MaterialTheme.typography.titleMedium)
                    }
                    Spacer(modifier = Modifier.height(height5))
                }
                if (!uiState.isSelectingFeedDateElement) {
                    Text(text = stringResource(id = R.string.pick_count_element),
                         color = MaterialTheme.colorScheme.primary,
                         style = MaterialTheme.typography.titleSmall)
                } else {
                    Text(text = stringResource(id = R.string.pick_date_element),
                         color = MaterialTheme.colorScheme.primary,
                         style = MaterialTheme.typography.titleSmall)
                }

                LazyColumn(modifier = Modifier.fillMaxSize()
                                              .border(border1, Color.Gray)
                                              .padding(padding5)
                    ) {
                    items(uiState.loadedFeed) { element ->
                        Text(modifier = Modifier.clickable {
                            if (uiState.isSelectingFeedDateElement) onSelectFeedDateElement.invoke(element)
                            else onSelectFeedElement.invoke(element)
                            onOpenDialogSelectedFeedElementClick.invoke()
                        },
                            text = element,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(height2))
                    }
                }
            }
        }
    }

    // DialogView for add feed
    DialogViewAddFeed(
        title = stringResource(id = R.string.adding_feed),
        feedNameValue = uiState.feedName,
        onFeedNameFieldChangeValue = onFeedNameFieldChange,
        feedElementNameValue = uiState.selectedFeedElement,
        onFeedElementFieldChangeValue = onSelectFeedElement,
        selectDateElement = selectDateElement,
        selectedFeedDateElement = uiState.feedDateElement,
        openDialog = uiState.openDialogSelectedFeedElement,
        onCancel = onCloseDialogSelectedFeedElement,
        onConfirm = onAddNewFeed,
        hasFeedNameError = uiState.feedNameError,
        errorFeedNameMessage = uiState.feedNameErrorMessage,
        hasUrlFeedError = uiState.selectedFeedElementError,
        errorUrlFeedMessage = uiState.selectedFeedElementErrorMessage,
    )

    // DialogView for change feed
    DialogViewChangeFeed(
        openDialog = uiState.openDialogChangeFeed,
        feedTitle = uiState.feedUpdateName,
        feedUrl = uiState.feedUpdateUrl,
        feedCountElement = uiState.feedUpdateMainElement,
        onFeedTitleFieldChangeValue = onFeedTitleFieldChangeValue,
        onFeedUrlFieldChangeValue = onFeedUrlFieldChangeValue,
        onFeedCountElementFieldChangeValue = onFeedCountElementFieldChangeValue,
        onClose = onCloseDialogChangeFeed,
        onSave = onSaveChanges
    )

    // DialogView for delete feed
    DialogViewDefault(
        textValue = uiState.feedName,
        title = stringResource(id = R.string.delete_feed) + " ${uiState.removedFeed}",
        openDialog = uiState.openDialogRemoveFeed,
        onCancel = onCloseDialogRemoveFeedClick,
        onConfirm = onRemoveFeedClick
    )

    // DialogView for error alert
    DialogViewDefault(
        title = stringResource(id = R.string.feed_url_error),
        openDialog = uiState.openDialogFeedUrlErrorElement,
        onlyCancel = true,
        onCancel = onCloseDialogFeedUrlError
    )

    // DialogView for connection error alert
    DialogViewDefault(
        title = stringResource(id = R.string.internet_error),
        openDialog = uiState.openDialogIsConnected,
        onlyCancel = true,
        onCancel = onCloseDialogIsConnected
    )

    // DialogView for empty project
    DialogViewDefault(
        title = stringResource(id = R.string.project_empty),
        openDialog = uiState.openDialogNotProject,
        onlyCancel = true,
        onCancel = onCloseDialogNotProject
    )

}

@Preview(showBackground = true)
@Composable
fun FeedsScreenPreview() {
    val feedsViewModel: FeedsViewModel = viewModel()
    DataViewerTheme {
        FeedsScreen(
            uiState = FeedsContract.State.initial(),
            onAddFeedClick = { feedsViewModel.intent(FeedsContract.Event.AddFeedClickEvent) },
            onSelectFeedElement = {  },
            onFeedUrlFieldChangeValue = {  },
            onSaveChanges = {  },
            onOpenChangeFeedDialog = {  },
            onFeedCountElementFieldChangeValue = {  },
            onSelectFeedDateElement = {  },
            onCloseDialogFeedUrlError = {  },
            onSwitchToFeedsListClick = {  },
            onRemoveFeedClick = {  },
            onAddNewFeed = {  },
            onSelectRemovedFeedNameClick = {  },
            onOpenDialogSelectedFeedElementClick = {  },
            onCloseDialogSelectedFeedElement = {  },
            onCloseDialogChangeFeed = {  },
            onCloseDialogRemoveFeedClick = {  },
            onCloseDialogNotProject = {  },
            onFeedNameFieldChange = {  },
            onFeedTitleFieldChangeValue = {  },
            onFeedUrlFieldChange = {  },
            onCloseDialogIsConnected = {  },
            updateProject = {  },
            selectDateElement = {  }
        )
    }
}