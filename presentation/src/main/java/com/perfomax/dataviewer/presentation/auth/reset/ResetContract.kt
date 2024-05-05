package com.perfomax.dataviewer.presentation.auth.reset

import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel

interface ResetContract:
    UnidirectionalViewModel<ResetContract.Event, ResetContract.State, ResetContract.Effect?> {

    data class State(
        val email: String,
        val emailError: Boolean
    ) {
        companion object {
            fun initial(): State = State(
                email = EMPTY,
                emailError = false
            )
            fun notCreate(): State = State(
                email = EMPTY,
                emailError = false
            )
        }
    }

    sealed interface Event {
        data object LoginEvent: Event
        data object RegisterEvent: Event
        data object ResetEvent: Event
        data class EmailChangeEvent(val email: String): Event
    }

    sealed interface Effect {
        data object Login : Effect
    }
}