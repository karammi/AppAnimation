package com.asad.appanimation.home.data.dataSource.remote

import com.asad.appanimation.core.data.dataSource.DataResult
import kotlinx.coroutines.flow.Flow

interface HomeRemoteDataSource {

    suspend fun getHomeData(): Flow<DataResult<List<HomeDataModel>>>

}