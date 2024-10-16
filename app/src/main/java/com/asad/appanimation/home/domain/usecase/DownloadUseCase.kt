package com.asad.appanimation.home.domain.usecase

import com.asad.appanimation.home.domain.repository.DownloadRepository
import javax.inject.Inject


class DownloadUseCase @Inject constructor(
    private val downloadRepository: DownloadRepository
) {
    suspend operator fun invoke(url: String): Long {
        return downloadRepository.downloadFile(url)
    }

}