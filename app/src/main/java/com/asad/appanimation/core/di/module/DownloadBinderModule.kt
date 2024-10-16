package com.asad.appanimation.core.di.module

import com.asad.appanimation.core.data.dataSource.download.Downloader
import com.asad.appanimation.core.data.dataSource.download.DownloaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DownloadBinderModule {

    @Binds
    abstract fun bindDownloader(downloader: DownloaderImpl): Downloader
}