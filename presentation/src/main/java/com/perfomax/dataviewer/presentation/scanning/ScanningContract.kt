package com.perfomax.dataviewer.presentation.scanning

import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel


interface ScanningContract:
    UnidirectionalViewModel<ScanningContract.Event, ScanningContract.State, ScanningContract.Effect?> {

    data class State(

        val feedUrl: String,
        val feedUrlError: Boolean,
        val feedUrlErrorMessage: String,

        val loadedFeed: List<String>,

        val feedSearchValue: String,
        val feedSearchValueError: Boolean,
        val isFeedScanningResponse: Boolean
    ) {
        companion object {
            fun initial(): State = State(
                feedUrl = EMPTY,
                feedUrlError = false,
                feedUrlErrorMessage = EMPTY,
                loadedFeed = emptyList(),
                feedSearchValue = EMPTY,
                feedSearchValueError = false,
                isFeedScanningResponse = false
            )
            fun notCreate(): State = State(
                feedUrl = EMPTY,
                feedUrlError = false,
                feedUrlErrorMessage = EMPTY,
                loadedFeed = emptyList(),
                feedSearchValue = EMPTY,
                feedSearchValueError = false,
                isFeedScanningResponse = false
            )
        }
    }

    sealed interface Event {
        data class FeedUrlChangeEvent(val feedUrl: String): Event
        data object ScanningFeedClickEvent: Event
        data class SearchFeedElementChangeEvent(val feedSearchElement: String): Event
        data object SearchFeedElementClickEvent: Event
    }

    sealed interface Effect {

    }

    }
