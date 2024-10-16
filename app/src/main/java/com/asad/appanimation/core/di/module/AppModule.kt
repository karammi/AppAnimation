package com.asad.appanimation.core.di.module

import android.app.DownloadManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDownloadManager(@ApplicationContext context: Context): DownloadManager =
        context.getSystemService(DownloadManager::class.java)
}