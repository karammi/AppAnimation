package com.asad.appanimation.home.data.dataSource.remote

interface HomeRemoteDataSource {

    suspend fun getHomeData(): List<HomeDataModel>
}