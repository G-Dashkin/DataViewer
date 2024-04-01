package com.perfomax.dataviewer.presentation.scanning

import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.presentation.projects.ProjectsContract
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel


interface ScanningContract:
    UnidirectionalViewModel<ScanningContract.Event, ScanningContract.State, ScanningContract.Effect?> {

    data class State(
        val projectName: String
    ) {
        companion object {
            fun initial(): State = State(
                projectName = EMPTY
            )
            fun notCreate(): State = State(
                projectName = EMPTY
            )
        }
    }

    sealed interface Event {
        data object SomeEvent: Event

    }

    sealed interface Effect {

    }

    }
