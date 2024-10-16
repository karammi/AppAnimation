package com.asad.appanimation.home.data.dataSource.download

interface DownloadRemoteDataSource {
    suspend fun downloadFile(url: String): Long

}