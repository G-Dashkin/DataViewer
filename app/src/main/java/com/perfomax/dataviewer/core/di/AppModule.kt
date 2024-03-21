package com.perfomax.dataviewer.core.di

import com.perfomax.dataviewer.data.network.api.FeedApi
import com.perfomax.dataviewer.data.network.api.FeedApiImpl
import com.perfomax.dataviewer.data.repository.FeedsRepositoryImpl
import com.perfomax.dataviewer.data.repository.ProjectsRepositoryImpl
import com.perfomax.dataviewer.data.storage.api.FeedsStorage
import com.perfomax.dataviewer.data.storage.api.ProjectsStorage
import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.repository.ProjectsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Singleton
    @Provides
    fun provideFeedApi(): FeedApi = FeedApiImpl()

    @Singleton
    @Provides
    fun provideProjectsRepository(
        projectsStorage: ProjectsStorage
    ): ProjectsRepository = ProjectsRepositoryImpl(projectsStorage)

    @Singleton
    @Provides
    fun provideFeedsRepository(
        feedApi: FeedApi,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        feedsStorage: FeedsStorage
    ): FeedsRepository = FeedsRepositoryImpl(feedApi, dispatcher, feedsStorage)
}