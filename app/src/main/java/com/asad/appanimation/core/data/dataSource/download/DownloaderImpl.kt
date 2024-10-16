package com.asad.appanimation.core.data.dataSource.download

import android.app.DownloadManager
import android.os.Environment
import androidx.core.net.toUri
import javax.inject.Inject

class DownloaderImpl @Inject constructor(
    private val downloadManager: DownloadManager
) : Downloader {

    override fun downloadFile(url: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("application/zip")
            .setTitle("Downloading ZIP File")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDescription("Please wait to download the file...")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "appAnimation_file.zip"
            )
        return downloadManager.enqueue(request)
    }
}