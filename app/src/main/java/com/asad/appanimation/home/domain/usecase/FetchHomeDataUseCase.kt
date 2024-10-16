package com.asad.appanimation.home.domain.usecase

import com.asad.appanimation.core.data.dataSource.DataResult
import com.asad.appanimation.home.data.dataSource.remote.HomeDataModel
import com.asad.appanimation.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchHomeDataUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): Flow<DataResult<List<HomeDataModel>>> =
        homeRepository.getHomeData()

}