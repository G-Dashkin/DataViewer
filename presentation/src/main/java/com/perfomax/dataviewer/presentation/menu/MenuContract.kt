package com.perfomax.dataviewer.presentation.menu

import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel

interface MenuContract:
    UnidirectionalViewModel<MenuContract.Event, MenuContract.State, MenuContract.Effect?> {

    data class State(
        val selectedProject: String,
        val authUser: String
    ) {
        companion object {
            fun initial(): State = State(
                selectedProject = EMPTY,
                authUser = EMPTY
            )
            fun notCreate(): State = State(
                selectedProject = EMPTY,
                authUser = EMPTY
            )
        }
    }

    sealed interface Event {
        data object UpdateProjectEvent : Event
    }

    sealed interface Effect

}