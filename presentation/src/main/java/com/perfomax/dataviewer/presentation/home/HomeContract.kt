package com.perfomax.dataviewer.presentation.home

import com.perfomax.dataviewer.domain.models.Feed
import com.perfomax.dataviewer.presentation.feeds.FeedsContract
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel

interface HomeContract:
    UnidirectionalViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect?> {

    data class State(
        val feedsList: List<Feed>,
    ) {
        companion object {
            fun initial(): State = State(
                feedsList = emptyList(),
            )
            fun notCorrect(): State = State(
                feedsList = emptyList(),
            )
        }
    }

    sealed interface Event {
        data object CountFeedElementEvent: Event
    }

    sealed interface Effect {
        data object Click : Effect
    }

}