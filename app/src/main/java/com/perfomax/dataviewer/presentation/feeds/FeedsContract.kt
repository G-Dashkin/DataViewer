package com.perfomax.dataviewer.presentation.feeds

import com.perfomax.dataviewer.core.base.UnidirectionalViewModel
import com.perfomax.dataviewer.domain.EMPTY

interface FeedsContract:
    UnidirectionalViewModel<FeedsContract.Event, FeedsContract.State, FeedsContract.Effect?> {

    data class State(
        val feedUrl: String,
        val feedUrlError: Boolean
    ) {
        companion object {
            fun initial(): State = State(
                feedUrl = EMPTY,
                feedUrlError = false
            )
            fun notCorrect(): State = State(
                feedUrl = EMPTY,
                feedUrlError = false
            )
        }
    }

    sealed interface Event {
        data object AddFeedClickEvent: Event
        data class FeedUrlChangeEvent(val text: String): Event
    }

    sealed interface Effect {
//        data object Click : Effect
    }

}