package com.perfomax.dataviewer.core.di

import android.content.Context
import com.perfomax.dataviewer.data.repository.DatastoreRepositoryImpl
import com.perfomax.dataviewer.domain.repository.DatastoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): DatastoreRepository = DatastoreRepositoryImpl(context)

}