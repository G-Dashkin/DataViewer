package com.perfomax.dataviewer.presentation.projects

import com.perfomax.dataviewer.core.base.UnidirectionalViewModel
import com.perfomax.dataviewer.domain.EMPTY

interface ProjectsContract:
    UnidirectionalViewModel<ProjectsContract.Event, ProjectsContract.State, ProjectsContract.Effect?> {

    data class State(
        val projectName: String,
        val projectNameError: Boolean,
        val removedProject: String,
        val projectsList: List<String>,
        val selectedProject: String
    ) {
        companion object {
            fun initial(): State = State(
                projectName = EMPTY,
                projectNameError = false,
                removedProject = EMPTY,
                projectsList = emptyList(),
                selectedProject = EMPTY
            )
            fun notCorrect(): State = State(
                projectName = EMPTY,
                projectNameError = false,
                removedProject = EMPTY,
                projectsList = emptyList(),
                selectedProject = EMPTY
            )
        }
    }

    sealed interface Event {
        data class ProjectNameChangeEvent(val projectName: String): Event
        data object CreateNewProjectClickEvent: Event
        data object ClearProjectNameFieldEvent: Event
        data class SelectRemovedProject(val removedProject: String): Event
        data object RemoveProjectClickEvent : Event
        data class SelectProject(val selectedProject: String): Event
    }

    sealed interface Effect {
        data object Click : Effect
    }

}