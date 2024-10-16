package com.asad.appanimation.core.di.module

import com.asad.appanimation.core.data.dataSource.CustomHttpInterceptor
import com.asad.appanimation.core.data.dataSource.CustomNetworkException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = "http://45.84.220.20:8081/"

    @Provides
    fun provideMoshi(): Moshi =Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
//    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    fun provideMoshiConverter(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        customHttpInterceptor: CustomHttpInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(customHttpInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: Lazy<OkHttpClient>,
        moshiConverterFactory: MoshiConverterFactory,
        baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory { okHttpClient.get().newCall(it) }
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitErrorConverter(retrofit: Retrofit): Converter<ResponseBody, CustomNetworkException> {
        return retrofit.responseBodyConverter(CustomNetworkException::class.java, arrayOf())
    }
}
