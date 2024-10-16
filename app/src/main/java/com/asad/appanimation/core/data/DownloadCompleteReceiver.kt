package com.asad.appanimation.core.data

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DownloadCompleteReceiver @Inject constructor(
//    private val downloadManager: DownloadManager
) : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "android.intent.action.DOWNLOAD_COMPLETE") {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
//            downloadManager.query(DownloadManager.Query().setFilterById(id))
            if (id != -1L) {
                println("Download with ID $id finished")
            }
        }
    }

    //handle fail download
}