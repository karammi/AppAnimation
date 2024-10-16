package com.asad.appanimation.home.data.dataSource.download

import com.asad.appanimation.core.data.dataSource.download.Downloader
import javax.inject.Inject

class DownloadRemoteDataSourceImpl @Inject constructor(
    private val downloader: Downloader
) : DownloadRemoteDataSource {
    override suspend fun downloadFile(url: String): Long =
        downloader.downloadFile(url)
}