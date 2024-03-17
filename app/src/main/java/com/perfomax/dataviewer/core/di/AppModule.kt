package com.perfomax.dataviewer.core.di

import com.perfomax.dataviewer.data.repository.ProjectsRepositoryImpl
import com.perfomax.dataviewer.data.storage.api.ProjectsStorage
import com.perfomax.dataviewer.domain.repository.ProjectsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideProjectsRepository(
        projectsStorage: ProjectsStorage
    ): ProjectsRepository = ProjectsRepositoryImpl(projectsStorage)
}