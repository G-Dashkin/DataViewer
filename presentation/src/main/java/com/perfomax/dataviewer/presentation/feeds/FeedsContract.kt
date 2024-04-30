package com.perfomax.dataviewer.presentation.feeds

import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel

interface FeedsContract:
    UnidirectionalViewModel<FeedsContract.Event, FeedsContract.State, FeedsContract.Effect?> {

    data class State(
        val feedUrl: String,
        val feedUrlError: Boolean,
        val feedUrlErrorMessage: String,
        val loadedFeed: List<String>,
        val feedsList: List<Feed>,
        val feedName: String,
        val feedNameError: Boolean,
        val feedNameErrorMessage: String,
        val feedUpdateSelectName: String,
        val feedUpdateName: String,
        val feedUpdateUrl: String,
        val feedUpdateMainElement: String,
        val feedElement: String,
        val selectedFeedElement: String,
        val selectedFeedElementError: Boolean,
        val selectedFeedElementErrorMessage: String,
        val isSelectingFeedDateElement: Boolean,
        val feedDateElement: String,
        val removedFeed: String,
        val isCountingFeedElements: Boolean,
        val openDialogSelectedFeedElement: Boolean,
        val openDialogFeedUrlErrorElement: Boolean,
        val openDialogChangeFeed: Boolean,
        val isFeedsList: Boolean,
        val openDialogRemoveFeed: Boolean

    ) {
        companion object {
            fun initial(): State = State(
                feedUrl = EMPTY,
                feedUrlError = false,
                feedUrlErrorMessage = EMPTY,
                loadedFeed = emptyList(),
                feedsList = emptyList(),
                feedName = EMPTY,
                feedNameError = false,
                feedNameErrorMessage = EMPTY,
                feedElement = EMPTY,
                selectedFeedElement = EMPTY,
                selectedFeedElementError = false,
                selectedFeedElementErrorMessage = EMPTY,
                isSelectingFeedDateElement = false,
                feedUpdateSelectName = EMPTY,
                feedUpdateName = EMPTY,
                feedUpdateUrl = EMPTY,
                feedUpdateMainElement = EMPTY,
                feedDateElement = EMPTY,
                removedFeed = EMPTY,
                isCountingFeedElements = false,
                openDialogSelectedFeedElement = false,
                isFeedsList = true,
                openDialogRemoveFeed = false,
                openDialogFeedUrlErrorElement = false,
                openDialogChangeFeed = false
            )
            fun notCorrect(): State = State(
                feedUrl = EMPTY,
                feedUrlError = false,
                feedUrlErrorMessage = EMPTY,
                loadedFeed = emptyList(),
                feedsList = emptyList(),
                feedName = EMPTY,
                feedNameError = false,
                feedNameErrorMessage = EMPTY,
                feedElement = EMPTY,
                selectedFeedElement = EMPTY,
                selectedFeedElementError = false,
                selectedFeedElementErrorMessage = EMPTY,
                isSelectingFeedDateElement = false,
                feedUpdateSelectName = EMPTY,
                feedUpdateName = EMPTY,
                feedUpdateUrl = EMPTY,
                feedUpdateMainElement = EMPTY,
                feedDateElement = EMPTY,
                removedFeed = EMPTY,
                isCountingFeedElements = false,
                openDialogSelectedFeedElement = false,
                isFeedsList = true,
                openDialogRemoveFeed = false,
                openDialogFeedUrlErrorElement = false,
                openDialogChangeFeed = false
            )
        }
    }

    sealed interface Event {
        data object AddFeedClickEvent: Event
        data class FeedUrlChangeEvent(val feedUrl: String): Event
        data class SelectFeedElementEvent(val selectedFeedElement: String): Event
        data class SelectFeedDateElementEvent(val selectedFeedDataElement: String): Event
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
        data object SelectDateElementInFeedEvent : Event
        data class OpenChangeFeedDialogEvent(val feedName: String): Event
        data object CloseDialogChangeFeedEvent : Event
        data object SaveFeedChangesEvent : Event

        data class FeedTitleUpdateChangeEvent(val feedTitle: String): Event
        data class FeedCountElementUpdateChangeEvent(val feedCountElement: String): Event
        data class FeedUrlUpdateChangeEvent(val feedUrl: String): Event
    }

    sealed interface Effect {
//        data object Click : Effect
    }

}