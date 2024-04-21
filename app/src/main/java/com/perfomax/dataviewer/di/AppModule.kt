package com.perfomax.dataviewer.di

import android.content.Context
import com.perfomax.dataviewer.app.Application
import com.perfomax.dataviewer.data.network.api.FeedApi
import com.perfomax.dataviewer.data.network.api.FeedApiImpl
import com.perfomax.dataviewer.data.repository.FeedsRepositoryImpl
import com.perfomax.dataviewer.data.repository.ProjectsRepositoryImpl
import com.perfomax.dataviewer.data.repository.SettingsRepositoryImpl
import com.perfomax.dataviewer.data.storage.api.FeedsStorage
import com.perfomax.dataviewer.data.storage.api.ProjectsStorage
import com.perfomax.dataviewer.data.storage.api.SettingsStorage
import com.perfomax.dataviewer.domain.repository.AlarmScheduler
import com.perfomax.dataviewer.domain.repository.FeedsRepository
import com.perfomax.dataviewer.domain.repository.ProjectsRepository
import com.perfomax.dataviewer.domain.repository.SettingsRepository
import com.perfomax.dataviewer.receivers.AlarmSchedulerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideSettingsRepository(
        settingsStorage: SettingsStorage
    ): SettingsRepository = SettingsRepositoryImpl(settingsStorage)

    @Singleton
    @Provides
    fun provideFeedUpdateScheduleReceiver(
        @ApplicationContext context: Context
    ): AlarmScheduler = AlarmSchedulerImpl(context)

}