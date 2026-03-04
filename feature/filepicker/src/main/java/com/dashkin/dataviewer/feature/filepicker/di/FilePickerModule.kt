package com.dashkin.dataviewer.feature.filepicker.di

import com.dashkin.dataviewer.feature.filepicker.data.repository.FileRepositoryImpl
import com.dashkin.dataviewer.feature.filepicker.domain.repository.FileRepository
import com.dashkin.dataviewer.feature.filepicker.presentation.viewmodel.FilePickerViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val filePickerModule = module {
    single<FileRepository> { FileRepositoryImpl(androidContext()) }
    viewModel { FilePickerViewModel(get()) }
}
