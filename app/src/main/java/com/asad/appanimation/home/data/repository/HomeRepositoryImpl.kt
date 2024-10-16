package com.asad.appanimation.home.data.repository

import com.asad.appanimation.core.data.dataSource.DataResult
import com.asad.appanimation.home.data.dataSource.remote.HomeDataModel
import com.asad.appanimation.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(): HomeRepository {
    override suspend fun getHomeData(): Flow<DataResult<List<HomeDataModel>>> {
        TODO("Not yet implemented")
    }
}