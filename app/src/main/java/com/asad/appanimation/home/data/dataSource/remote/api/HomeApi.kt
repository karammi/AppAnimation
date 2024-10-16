package com.asad.appanimation.home.data.dataSource.remote.api

import com.asad.appanimation.home.data.dataSource.remote.HomeDataModel
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeApi {

    @GET("mock/asset")
    suspend fun getHomeData(): List<HomeDataModel>
}