package com.asad.appanimation.home.data.dataSource.remote

import com.asad.appanimation.core.data.dataSource.ApiRunner
import com.asad.appanimation.core.data.dataSource.DataResult
import com.asad.appanimation.home.data.dataSource.remote.api.HomeApi
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class HomeRemoteDataSourceImpl @Inject constructor(
    private val apiRunner: ApiRunner,
    private val homeApi: HomeApi
) : HomeRemoteDataSource {
    override suspend fun getHomeData(): Flow<DataResult<List<HomeDataModel>>> =
        apiRunner
            .invokeSuspendedFlow { homeApi.getHomeData() }

}