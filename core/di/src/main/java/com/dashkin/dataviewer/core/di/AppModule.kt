package com.dashkin.dataviewer.core.di

import org.koin.core.module.Module

/**
 * Base list of core Koin modules shared across the app.
 * Feature modules register themselves via [DataViewerApplication].
 */
val appModules: List<Module> = emptyList()
