package com.dashkin.dataviewer.feature.nodedetail.di

import com.dashkin.dataviewer.feature.nodedetail.presentation.viewmodel.NodeDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val nodeDetailModule = module {
    viewModel { NodeDetailViewModel() }
}
