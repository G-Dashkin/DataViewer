package com.dashkin.dataviewer

import android.app.Application
import com.dashkin.dataviewer.core.di.appModules
import com.dashkin.dataviewer.feature.filepicker.di.filePickerModule
import com.dashkin.dataviewer.feature.nodedetail.di.nodeDetailModule
import com.dashkin.dataviewer.feature.search.di.searchModule
import com.dashkin.dataviewer.feature.treeview.di.treeViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DataViewerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@DataViewerApplication)
            modules(
                appModules +
                listOf(
                    filePickerModule,
                    treeViewModule,
                    searchModule,
                    nodeDetailModule
                )
            )
        }
    }
}
