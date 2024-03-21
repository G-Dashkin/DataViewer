package com.perfomax.dataviewer.presentation.menu

import com.perfomax.dataviewer.core.base.UnidirectionalViewModel
import com.perfomax.dataviewer.domain.EMPTY

interface MenuContract:
    UnidirectionalViewModel<MenuContract.Event, MenuContract.State, MenuContract.Effect?> {

    data class State(
        val selectedProject: String
    ) {
        companion object {
            fun initial(): State = State(
                selectedProject = EMPTY
            )
            fun notCreate(): State = State(
                selectedProject = EMPTY
            )
        }
    }

    sealed interface Event {
        data class UpdateProjectEvent(val updatedProjectName: String) : Event

    }

    sealed interface Effect {

    }


}