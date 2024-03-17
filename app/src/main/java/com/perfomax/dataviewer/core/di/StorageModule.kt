package com.perfomax.dataviewer.core.di

import com.perfomax.dataviewer.data.storage.api.ProjectsStorage
import com.perfomax.dataviewer.data.storage.memory.ProjectsStorageImpl
import com.perfomax.dataviewer.data.datastore.api.ProjectsDataStore
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
}