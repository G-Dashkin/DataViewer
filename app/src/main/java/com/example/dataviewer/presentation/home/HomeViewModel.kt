package com.example.dataviewer.presentation.home

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface MenuUiState {
//    data object Loading : MenuUiState
    object Loading : MenuUiState

    @Immutable
    data class Success(
        val feedsList: ImmutableList<String>,
        val selectedSectionIndex: Int = 0,
//        val product: ImmutableList<MenuItem>
    ): MenuUiState {
        fun selectionTitle(): String = feedsList[selectedSectionIndex]
    }
}

class HomeViewModel: ViewModel() {

    private val _uiStateMenu = MutableStateFlow<MenuUiState>(MenuUiState.Loading)
    val uiStateMenu = _uiStateMenu.asStateFlow()



//    internal class Factory(
//        private val getMenuSectionsUseCase: GetMenuSectionsUseCase,
//        private val getMenuItemsUseCase: GetMenuItemsUseCase,
//        private val addCartUseCase: AddCartUseCase
//    ) : ViewModelProvider.NewInstanceFactory() {
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T =
//            HomeViewModel(getMenuSectionsUseCase, getMenuItemsUseCase, addCartUseCase) as T
//    }
}