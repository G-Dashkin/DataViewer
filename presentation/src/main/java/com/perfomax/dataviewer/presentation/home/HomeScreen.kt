package com.perfomax.dataviewer.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.perfomax.dataviewer.domain.utils.getFeedElementValue
import com.perfomax.dataviewer.ui.theme.DataViewerTheme
import com.perfomax.dataviewer.ui.theme.height50
import com.perfomax.dataviewer.ui.theme.padding15
import com.perfomax.dataviewer.ui.theme.shape8
import com.perfomax.dataviewer.ui.theme.zeroVal
import com.perfomax.dataviewer.ui.widgets.FeedItem
import com.perfomax.dataviewer.ui.widgets.HomeScreenFeedDialogView
import com.perfomax.dataviewer.ui.widgets.LoadingIndicator
import com.perfomax.ui.R

@Composable
fun HomeScreen(
    uiState: HomeContract.State,
    updateFeedsList:() -> Unit,
    updateBackgroundUpdate:() -> Unit,
    onUpdateFeedsClick:() -> Unit,

    onClickFeedElement:(String) -> Unit,
    onChangeFeed:(String) -> Unit,
    onFindFeedElement:(String) -> Unit,
    onFindSelectedElement:(String) -> Unit,
    onClickUpdateFeed:() -> Unit,
    onCloseDialogClick:() -> Unit,
) {

    updateFeedsList.invoke()
    updateBackgroundUpdate.invoke()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding15),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(height50)
            .defaultMinSize(minHeight = height50),
            enabled = !uiState.isUpdatingFeedList,
            shape = RoundedCornerShape(shape8),
            contentPadding = PaddingValues(zeroVal),
            onClick = onUpdateFeedsClick
        ) {
            Text(text = stringResource(id = R.string.feed_update),
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleLarge)
            }

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
                FeedItem(
                    feedName = element.feedName,
                    countElements = element.feedElementCount,
                    updateTime = element.feedUpdateTime.getFeedElementValue(),
                    loadTime = element.feedLoadTime,
                    isClickableElement = true,
                    onClickFeedElement = onClickFeedElement,
                    onOpenChangeFeedDialog = onChangeFeed
                )
            }
        }

        HomeScreenFeedDialogView(
            feedTitle = uiState.selectedFeedName,
            feedUrl = uiState.selectedFeedUrl,
            feedFindElementValue = uiState.findFeedElement,
            onFeedFindElementChangeValue = onFindFeedElement,
            onFindElementsInFeed = onFindSelectedElement,
            openDialog = uiState.openDialogHomeScreenFeed,
            onUpdateFeed = onClickUpdateFeed,
            onClose = onCloseDialogClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DataViewerTheme {
        val homeViewModel: HomeViewModel = viewModel()
        val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
        val homeEffects by homeViewModel.effect.collectAsStateWithLifecycle()
//        HomeScreen (
//            uiState = homeUiState,
//            updateFeedsList = {},
//            onUpdateFeedsClick = {},
//            onClickFeedElement = {}
//        )
    }
}