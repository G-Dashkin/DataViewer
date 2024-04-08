package com.perfomax.dataviewer.di

import com.perfomax.dataviewer.data.datastore.api.FeedsDataStore
import com.perfomax.dataviewer.data.storage.api.ProjectsStorage
import com.perfomax.dataviewer.data.storage.memory.ProjectsStorageImpl
import com.perfomax.dataviewer.data.datastore.api.ProjectsDataStore
import com.perfomax.dataviewer.data.datastore.api.SettingsDataStore
import com.perfomax.dataviewer.data.storage.api.FeedsStorage
import com.perfomax.dataviewer.data.storage.api.SettingsStorage
import com.perfomax.dataviewer.data.storage.memory.FeedsStorageImpl
import com.perfomax.dataviewer.data.storage.memory.SettingsStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {
    @Singleton
    @Provides
    fun provideProjectsStorage(
        projectsDataStore: ProjectsDataStore
    ): ProjectsStorage = ProjectsStorageImpl(datastore = projectsDataStore)

    @Singleton
    @Provides
    fun provideFeedsStorage(
        feedsDataStore: FeedsDataStore
    ): FeedsStorage = FeedsStorageImpl(datastore = feedsDataStore)

    @Singleton
    @Provides
    fun provideSettingsStorage(
        settingsDataStore: SettingsDataStore
    ): SettingsStorage = SettingsStorageImpl(datastore = settingsDataStore)
}