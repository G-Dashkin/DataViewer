package com.perfomax.dataviewer.presentation.feeds

import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel

interface FeedsContract:
    UnidirectionalViewModel<FeedsContract.Event, FeedsContract.State, FeedsContract.Effect?> {

    data class State(
        val feedUrl: String,
        val feedUrlError: Boolean,
        val loadedFeed: List<String>,
        val feedsList: List<Feed>,

        val feedName: String,
        val feedNameError: Boolean,
        val feedNameErrorMessage: String,

        val selectedFeedElement: String,
        val selectedFeedElementError: Boolean,
        val selectedFeedElementErrorMessage: String,

        val removedFeed: String,

        val openDialogSelectedFeedElement: Boolean,
        val openDialogFeedUrlErrorElement: Boolean,

        val isFeedsList: Boolean,
        val openDialogRemoveFeed: Boolean

    ) {
        companion object {
            fun initial(): State = State(
                feedUrl = EMPTY,
                feedUrlError = false,
                loadedFeed = emptyList(),
                feedsList = emptyList(),
                feedName = EMPTY,
                feedNameError = false,
                feedNameErrorMessage = EMPTY,
                selectedFeedElement = EMPTY,
                selectedFeedElementError = false,
                selectedFeedElementErrorMessage = EMPTY,
                removedFeed = EMPTY,
                openDialogSelectedFeedElement = false,
                isFeedsList = true,
                openDialogRemoveFeed = false,
                openDialogFeedUrlErrorElement = false
            )
            fun notCorrect(): State = State(
                feedUrl = EMPTY,
                feedUrlError = false,
                loadedFeed = emptyList(),
                feedsList = emptyList(),
                feedName = EMPTY,
                feedNameError = false,
                feedNameErrorMessage = EMPTY,
                selectedFeedElement = EMPTY,
                selectedFeedElementError = false,
                selectedFeedElementErrorMessage = EMPTY,
                removedFeed = EMPTY,
                openDialogSelectedFeedElement = false,
                isFeedsList = true,
                openDialogRemoveFeed = false,
                openDialogFeedUrlErrorElement = false
            )
        }
    }

    sealed interface Event {
        data object AddFeedClickEvent: Event
        data class FeedUrlChangeEvent(val text: String): Event
        data class SelectFeedElementEvent(val selectedFeedElement: String): Event
        data class SelectRemovedFeedEvent(val removedFeed: String): Event
        data object RemoveFeedClickEvent : Event
        data class FeedNameEvent(val feedName: String): Event
        data object OpenDialogSelectedFeedElementEvent: Event
        data object CloseDialogSelectedFeedElementEvent : Event
        data object AddNewFeedEvent : Event
        data object CloseDialogRemoveEvent : Event
        data object UpdateProjectEvent : Event
        data object SwitchScreenToFeedsListEvent : Event
        data object CloseDialogFeedUrlErrorEvent : Event

    }

    sealed interface Effect {
//        data object Click : Effect
    }

}