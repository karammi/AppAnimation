package com.asad.appanimation.home.data.repository

import com.asad.appanimation.home.data.dataSource.download.DownloadRemoteDataSource
import com.asad.appanimation.home.domain.repository.DownloadRepository
import javax.inject.Inject


class DownloadRepositoryImpl @Inject constructor(
    private val downloadRemoteDataSource: DownloadRemoteDataSource
) : DownloadRepository {
    override suspend fun downloadFile(url: String): Long {
        return downloadRemoteDataSource.downloadFile(url)
    }
}