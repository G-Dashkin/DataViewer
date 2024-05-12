package com.perfomax.dataviewer.presentation.scanning

import androidx.compose.foundation.lazy.LazyListState
import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.presentation.feeds.FeedsContract
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel


interface ScanningContract:
    UnidirectionalViewModel<ScanningContract.Event, ScanningContract.State, ScanningContract.Effect?> {

    data class State(

        val feedUrl: String,
        val feedUrlError: Boolean,
        val feedUrlErrorMessage: String,
        val loadedFeed: List<String>,
        val isScanningFeed: Boolean,
        val openDialogIsConnected: Boolean,
        val openDialogIsNotFind: Boolean,
        val feedSearchValue: String,
        val feedSearchValueError: Boolean,
        val isFeedScanningResponse: Boolean,

        val listState: LazyListState
    ) {
        companion object {
            fun initial(): State = State(
                feedUrl = EMPTY,
                feedUrlError = false,
                feedUrlErrorMessage = EMPTY,
                loadedFeed = emptyList(),
                isScanningFeed = false,
                openDialogIsConnected = false,
                openDialogIsNotFind = false,
                feedSearchValue = EMPTY,
                feedSearchValueError = false,
                isFeedScanningResponse = false,
                listState = LazyListState()
            )
            fun notCreate(): State = State(
                feedUrl = EMPTY,
                feedUrlError = false,
                feedUrlErrorMessage = EMPTY,
                loadedFeed = emptyList(),
                isScanningFeed = false,
                openDialogIsConnected = false,
                openDialogIsNotFind = false,
                feedSearchValue = EMPTY,
                feedSearchValueError = false,
                isFeedScanningResponse = false,
                listState = LazyListState()
            )
        }
    }

    sealed interface Event {
        data class FeedUrlChangeEvent(val feedUrl: String): Event
        data class SearchFeedElementChangeEvent(val feedSearchElement: String): Event
        data object LoadingFeedClickEvent: Event
        data object SearchFeedElementClickEvent: Event
        data object CloseDialogIsConnectedEvent : Event
        data object CloseDialogIsNotFindEvent : Event
    }

    sealed interface Effect {

    }

    }
