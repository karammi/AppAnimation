package com.asad.appanimation.core.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatcherModule {

    @Provides
//    @IODispatcherQualifier
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
//    @MainDispatcherQualifier
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
//    @DefaultDispatcherQualifier
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
