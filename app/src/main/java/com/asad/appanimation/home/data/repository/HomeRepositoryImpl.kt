package com.asad.appanimation.home.data.repository

import com.asad.appanimation.core.data.dataSource.DataResult
import com.asad.appanimation.home.data.dataSource.remote.HomeDataModel
import com.asad.appanimation.home.data.dataSource.remote.HomeRemoteDataSource
import com.asad.appanimation.home.domain.repository.HomeRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource
) : HomeRepository {
    override suspend fun getHomeData(): Flow<DataResult<List<HomeDataModel>>> =
        homeRemoteDataSource.getHomeData()

}