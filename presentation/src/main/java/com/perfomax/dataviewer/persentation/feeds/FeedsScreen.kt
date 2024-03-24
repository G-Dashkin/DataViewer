package com.perfomax.dataviewer.persentation.feeds

import android.util.Log
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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.perfomax.dataviewer.core.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.core.ui.widgets.DefaultDialogView
import com.perfomax.dataviewer.core.ui.widgets.FeedItem
import com.perfomax.dataviewer.core.ui.widgets.FeedsDialogView
import com.perfomax.dataviewer.core.ui.widgets.FeedsScreenFormTextField
import com.perfomax.dataviewer.core.ui.widgets.LoadingIndicator
import com.perfomax.dataviewer.core.ui.widgets.ProjectItem
import com.perfomax.dataviewer.core.ui.widgets.ProjectsDialogView
import com.perfomax.dataviewer.core.utils.getFeedName

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
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(modifier = Modifier,
            fontSize = 18.sp,
            text =  "Загрузить новый фид",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            FeedsScreenFormTextField(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp)
                    .defaultMinSize(minHeight = 50.dp)
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(0.dp),

                text = uiState.feedUrl,
                labelText = "Feed Url",
                isError = uiState.feedUrlError,
                onChange = onFeedUrlFieldChange
            )
            Spacer(modifier = Modifier.width(10.dp))

            if (uiState.isFeedsList){
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .defaultMinSize(minHeight = 50.dp),
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(
                        start = 0.dp,
                        top = 0.dp,
                        end = 0.dp,
                        bottom = 0.dp,
                    ),
                    onClick = onAddFeedClick
                ) {
                    Text(text = "Загрузить", fontSize = 18.sp)
                }
            } else {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .defaultMinSize(minHeight = 50.dp),
                    shape = RoundedCornerShape(5.dp),
                    contentPadding = PaddingValues(
                        start = 0.dp,
                        top = 0.dp,
                        end = 0.dp,
                        bottom = 0.dp,
                    ),
                    onClick = onSwitchToFeedsListClick
                ) {
                    Text(text = "Отменить", fontSize = 18.sp)
                }
            }


        }
        Spacer(modifier = Modifier.height(5.dp))
        Column(modifier = Modifier.fillMaxSize()) {
            if (uiState.isFeedsList){
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.feedsList) { element ->
                        FeedItem(
                            feedElement = element.getFeedName(),
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
                            modifier = Modifier.background(Color.Gray)
                                .clickable {
                                    onSelectFeedElement.invoke(element)
                                    onOpenDialogSelectedFeedElementClick.invoke()
                                },
                            text = element,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
            }
        }
    }

    FeedsDialogView(
        title = "Добвление фида",
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
        title = "Удалить фид ${uiState.removedFeed}",
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