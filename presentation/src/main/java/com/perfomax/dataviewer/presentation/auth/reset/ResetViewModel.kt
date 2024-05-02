package com.perfomax.dataviewer.presentation.auth.reset

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.presentation.auth.login.LoginContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ResetViewModel: ViewModel(), ResetContract {

    private val _uiState = MutableStateFlow(ResetContract.State.initial())
    private val _effect = MutableStateFlow<ResetContract.Effect?>(null)

    override val uiState: StateFlow<ResetContract.State> = _uiState.asStateFlow()
    override val effect: StateFlow<ResetContract.Effect?> = _effect.asStateFlow()

    override fun intent(event: ResetContract.Event) {
        when(event) {
            is ResetContract.Event.EmailChangeEvent -> {}
            ResetContract.Event.LoginEvent -> {}
            ResetContract.Event.RegisterEvent -> {}
            ResetContract.Event.ResetEvent -> {}
        }
    }

    override fun consume() {
        _effect.update { null }
    }

}