package com.perfomax.dataviewer.presentation.projects

import com.perfomax.dataviewer.core.base.UnidirectionalViewModel
import com.perfomax.dataviewer.domain.EMPTY

interface ProjectsContract:
    UnidirectionalViewModel<ProjectsContract.Event, ProjectsContract.State, ProjectsContract.Effect?> {

    data class State(
        val projectName: String,
        val projectNameError: Boolean
    ) {
        companion object {
            fun initial(): State = State(
                projectName = EMPTY,
                projectNameError = false
            )
            fun notCorrect(): State = State(
                projectName = EMPTY,
                projectNameError = false
            )
        }
    }

    sealed interface Event {
        data object CreateNewProjectClickEvent: Event
        data object GetProjectClickEvent: Event
        data class ProjectNameChangeEvent(val projectName: String): Event
        data object ClearProjectNameEvent: Event
    }

    sealed interface Effect {
        data object Click : Effect
    }

}