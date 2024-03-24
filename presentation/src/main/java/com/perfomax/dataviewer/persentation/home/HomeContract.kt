package com.perfomax.dataviewer.persentation.home

import com.perfomax.dataviewer.core.base.UnidirectionalViewModel
import com.perfomax.dataviewer.domain.EMPTY

interface HomeContract:
    UnidirectionalViewModel <HomeContract.Event, HomeContract.State, HomeContract.Effect?> {

    data class State(
        val feedsList: List<String>,
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
    }

    sealed interface Effect {
        data object Click : Effect
    }

}