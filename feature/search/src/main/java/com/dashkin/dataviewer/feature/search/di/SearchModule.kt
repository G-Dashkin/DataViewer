package com.dashkin.dataviewer.feature.search.di

import com.dashkin.dataviewer.feature.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel { SearchViewModel() }
}
