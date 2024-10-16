package com.asad.appanimation.home.di.module

import com.asad.appanimation.home.data.dataSource.download.DownloadRemoteDataSource
import com.asad.appanimation.home.data.dataSource.download.DownloadRemoteDataSourceImpl
import com.asad.appanimation.home.data.dataSource.remote.HomeRemoteDataSource
import com.asad.appanimation.home.data.dataSource.remote.HomeRemoteDataSourceImpl
import com.asad.appanimation.home.data.repository.DownloadRepositoryImpl
import com.asad.appanimation.home.data.repository.HomeRepositoryImpl
import com.asad.appanimation.home.domain.repository.DownloadRepository
import com.asad.appanimation.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeBinderModule {

    @Binds
    abstract fun bindHomeRepository(homeRepository: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindHomeRemoteDataSource(homeRemoteDataSource: HomeRemoteDataSourceImpl): HomeRemoteDataSource

    @Binds
    abstract fun bindDownloadRepository(downloadRepository: DownloadRepositoryImpl): DownloadRepository

    @Binds
    abstract fun bindDownloadRemoteDataSource(downloadRemoteDataSource: DownloadRemoteDataSourceImpl): DownloadRemoteDataSource

}