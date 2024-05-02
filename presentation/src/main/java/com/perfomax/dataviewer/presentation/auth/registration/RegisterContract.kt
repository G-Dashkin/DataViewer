package com.perfomax.dataviewer.presentation.auth.registration

import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.ui.base.UnidirectionalViewModel

interface RegisterContract:
    UnidirectionalViewModel<RegisterContract.Event, RegisterContract.State, RegisterContract.Effect?> {

    data class State(
        val login: String,
        val loginError: Boolean,
        val firstName: String,
        val firstNameError: Boolean,
        val password: String,
        val passwordError: Boolean
    ) {
        companion object {
            fun initial(): State = State(
                login = EMPTY,
                loginError = false,
                firstName = EMPTY,
                firstNameError = false,
                password = EMPTY,
                passwordError = false
            )
            fun notCreate(): State = State(
                login = EMPTY,
                loginError = false,
                firstName = EMPTY,
                firstNameError = false,
                password = EMPTY,
                passwordError = false
            )
        }
    }

    sealed interface Event {
        data object LoginEvent: Event
        data object RegisterEvent: Event
        data object ResetEvent: Event
        data class FirstNameChangeEvent(val firstName: String): Event
        data class EmailChangeEvent(val email: String): Event
        data class PasswordChangeEvent(val password: String): Event
    }

    sealed interface Effect {
        data object Register : Effect
        data object Reset : Effect
        data object SingIn : Effect

    }
}