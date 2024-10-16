package com.asad.appanimation.home.domain.repository

interface DownloadRepository {
    suspend fun downloadFile(url: String): Long
}