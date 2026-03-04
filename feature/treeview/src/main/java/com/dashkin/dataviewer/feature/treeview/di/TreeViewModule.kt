package com.dashkin.dataviewer.feature.treeview.di

import com.dashkin.dataviewer.feature.treeview.presentation.viewmodel.TreeViewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val treeViewModule = module {
    viewModel { TreeViewViewModel() }
}
