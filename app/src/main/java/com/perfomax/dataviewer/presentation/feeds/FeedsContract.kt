package com.perfomax.dataviewer.presentation.feeds

import com.perfomax.dataviewer.core.base.UnidirectionalViewModel
import com.perfomax.dataviewer.domain.EMPTY

interface FeedsContract:
    UnidirectionalViewModel<FeedsContract.Event, FeedsContract.State, FeedsContract.Effect?> {

    data class State(
        val feedUrl: String,
        val feedUrlError: Boolean,
        val loadedFeed: List<String>,

        val feedName: String,
        val selectedFeedElement: String,

        val openDialogSelectedFeedElement: Boolean,

    ) {
        companion object {
            fun initial(): State = State(
                feedUrl = EMPTY,
                feedUrlError = false,
                loadedFeed = emptyList(),
                feedName = EMPTY,
                selectedFeedElement = EMPTY,
                openDialogSelectedFeedElement = false
            )
            fun notCorrect(): State = State(
                feedUrl = EMPTY,
                feedUrlError = false,
                loadedFeed = emptyList(),
                feedName = EMPTY,
                selectedFeedElement = EMPTY,
                openDialogSelectedFeedElement = false
            )
        }
    }

    sealed interface Event {
        data object AddFeedClickEvent: Event
        data class FeedUrlChangeEvent(val text: String): Event
        data class SelectFeedElementEvent(val selectedFeedElement: String): Event
        data class FeedNameEvent(val feedName: String): Event
        data object OpenDialogSelectedFeedElementEvent: Event
        data object CloseDialogSelectedFeedElementEvent : Event
        data object AddNewFeedEvent : Event
    }

    sealed interface Effect {
//        data object Click : Effect
    }

}