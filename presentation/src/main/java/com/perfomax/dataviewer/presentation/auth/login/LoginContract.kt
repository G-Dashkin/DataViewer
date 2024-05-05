package com.perfomax.dataviewer.presentation.auth.login

import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel

interface LoginContract:
    UnidirectionalViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect?> {

    data class State(
        val login: String,
        val loginError: Boolean,
        val password: String,
        val passwordError: Boolean
    ) {
        companion object {
            fun initial(): State = State(
                login = EMPTY,
                loginError = false,
                password = EMPTY,
                passwordError = false
            )
            fun notCreate(): State = State(
                login = EMPTY,
                loginError = false,
                password = EMPTY,
                passwordError = false
            )
        }
    }

    sealed interface Event {
        data object LoginEvent: Event
        data object RegisterEvent: Event
        data object ResetEvent: Event
        data class EmailChangeEvent(val email: String): Event
        data class PasswordChangeEvent(val password: String): Event
    }

    sealed interface Effect {
        data object Login : Effect
        data object Register : Effect
        data object Reset : Effect
    }
}