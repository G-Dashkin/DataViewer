package com.perfomax.dataviewer.presentation.auth.login

import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel

interface LoginContract:
    UnidirectionalViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect?> {

    data class State(
        val login: String,
        val loginError: Boolean,
        val loginErrorMessage: String,
        val password: String,
        val passwordError: Boolean,
        val passwordErrorMessage: String
    ) {
        companion object {
            fun initial(): State = State(
                login = EMPTY,
                loginError = false,
                loginErrorMessage = EMPTY,
                password = EMPTY,
                passwordError = false,
                passwordErrorMessage = EMPTY
            )
            fun notLogin(): State = State(
                login = EMPTY,
                loginError = false,
                loginErrorMessage = EMPTY,
                password = EMPTY,
                passwordError = false,
                passwordErrorMessage = EMPTY
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