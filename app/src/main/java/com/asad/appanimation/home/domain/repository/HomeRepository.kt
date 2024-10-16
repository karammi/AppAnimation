package com.asad.appanimation.home.domain.repository

import com.asad.appanimation.core.data.dataSource.DataResult
import com.asad.appanimation.home.data.dataSource.remote.HomeDataModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getHomeData(): Flow<DataResult<List<HomeDataModel>>>
}