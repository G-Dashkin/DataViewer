package com.perfomax.dataviewer.persentation.projects

import com.perfomax.dataviewer.core.base.UnidirectionalViewModel
import com.perfomax.dataviewer.domain.EMPTY

interface ProjectsContract:
    UnidirectionalViewModel<ProjectsContract.Event, ProjectsContract.State, ProjectsContract.Effect?> {

    data class State(
        val projectName: String,
        val projectNameError: Boolean,
        val errorMessage: String,
        val removedProject: String,
        val projectsList: List<String>,
        val selectedProject: String,
        val openDialogCreateNewProject: Boolean,
        val openDialogRemoveProject: Boolean
    ) {
        companion object {
            fun initial(): State = State(
                projectName = EMPTY,
                projectNameError = false,
                errorMessage = EMPTY,
                removedProject = EMPTY,
                projectsList = emptyList(),
                selectedProject = EMPTY,
                openDialogCreateNewProject = false,
                openDialogRemoveProject = false
            )
            fun notCreate(): State = State(
                projectName = EMPTY,
                projectNameError = false,
                errorMessage = EMPTY,
                removedProject = EMPTY,
                projectsList = emptyList(),
                selectedProject = EMPTY,
                openDialogCreateNewProject = false,
                openDialogRemoveProject = false
            )
        }
    }

    sealed interface Event {
        data class ProjectNameChangeEvent(val projectName: String): Event
        data object CreateNewProjectClickEvent: Event
        data object RemoveProjectClickEvent : Event
        data class SelectProjectEvent(val selectedProject: String): Event
        data class SelectRemovedProjectEvent(val removedProject: String): Event
        data object OpenDialogCreateEvent : Event
        data object CloseDialogCreateEvent : Event
        data object CloseDialogRemoveEvent : Event
    }

    sealed interface Effect {

    }

}