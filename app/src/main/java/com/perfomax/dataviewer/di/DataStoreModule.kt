package com.perfomax.dataviewer.di

import android.content.Context
import com.perfomax.dataviewer.data.datastore.api.FeedsDataStore
import com.perfomax.dataviewer.data.datastore.memory.ProjectsDataStoreImpl
import com.perfomax.dataviewer.data.datastore.api.ProjectsDataStore
import com.perfomax.dataviewer.data.datastore.api.SettingsDataStore
import com.perfomax.dataviewer.data.datastore.memory.FeedsDataStoreImpl
import com.perfomax.dataviewer.data.datastore.memory.SettingsDataStoreImpl
import com.perfomax.dataviewer.data.storage.api.FeedsStorage
import com.perfomax.dataviewer.data.storage.memory.FeedsStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Singleton
    @Provides
    fun provideProjectsDatastore(
        @ApplicationContext context: Context
    ): ProjectsDataStore = ProjectsDataStoreImpl(context)

    @Singleton
    @Provides
    fun provideFeedsDatastore(
        @ApplicationContext context: Context
    ): FeedsDataStore = FeedsDataStoreImpl(context)

    @Singleton
    @Provides
    fun provideSettingsDatastore(
        @ApplicationContext context: Context
    ): SettingsDataStore = SettingsDataStoreImpl(context)
}