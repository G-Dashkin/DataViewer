package com.perfomax.dataviewer.presentation.home

import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel

interface HomeContract:
    UnidirectionalViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect?> {

    data class State(
        val feedsList: List<Feed>,
        val isUpdatingFeedList: Boolean,
        val isOnAlertCountFeedDifference: Boolean,
        val openDialogIsConnected: Boolean,
        val selectedFeedName: String,
        val selectedFeedUrl: String,
        val findFeedElement: String,
        val openDialogHomeScreenFeed: Boolean
    ) {
        companion object {
            fun initial(): State = State(
                feedsList = emptyList(),
                isUpdatingFeedList = false,
                isOnAlertCountFeedDifference = false,
                openDialogIsConnected = false,
                selectedFeedName = EMPTY,
                selectedFeedUrl = EMPTY,
                findFeedElement = EMPTY,
                openDialogHomeScreenFeed = false

            )
            fun notCorrect(): State = State(
                feedsList = emptyList(),
                isUpdatingFeedList = false,
                isOnAlertCountFeedDifference = false,
                openDialogIsConnected = false,
                selectedFeedName = EMPTY,
                selectedFeedUrl = EMPTY,
                findFeedElement = EMPTY,
                openDialogHomeScreenFeed = false
            )
        }
    }

    sealed interface Event {
        data object UpdateFeedsListEvent: Event
        data object UpdateBackgroundEvent: Event
        data object CountFeedElementEvent: Event
        data class ClickFeedNameEvent(val feedName: String): Event
        data class ClickFindFeedElement(val findFeedElement: String): Event
        data object UpdateFeedEvent: Event
        data object CloseDialogClickEvent: Event
        data object CloseDialogIsConnectedEvent : Event
        data class ScanningEvent(val feedUrl: String) : Event
    }

    sealed interface Effect {
        data class Scanning(val feedUrl: String) : Effect
    }

}