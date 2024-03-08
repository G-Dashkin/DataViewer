package com.perfomax.dataviewer.presentation.home

import com.perfomax.dataviewer.core.base.UnidirectionalViewModel
import com.perfomax.dataviewer.domain.EMPTY

interface HomeContract : UnidirectionalViewModel <HomeContract.Event, HomeContract.State, HomeContract.Effect?> {

    data class State(
        val text: String,
        val textError: Boolean
    ) {
        companion object {
            fun initial(): State = State(
                text = EMPTY,
                textError = false
            )
            fun notCorrect(): State = State(
                text = EMPTY,
                textError = false
            )
        }
    }

    sealed interface Event {
        data object ClickEvent: Event
        data class TextChangeEvent(val text: String): Event
    }

    sealed interface Effect {
        data object Click : Effect
    }

}