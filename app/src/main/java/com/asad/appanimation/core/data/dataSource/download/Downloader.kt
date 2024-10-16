package com.asad.appanimation.core.data.dataSource.download

interface Downloader {
    fun downloadFile(url: String): Long
}