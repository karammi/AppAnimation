package com.asad.appanimation.core.data

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import javax.inject.Inject

@AndroidEntryPoint
class DownloadCompleteReceiver @Inject constructor(
//    private val downloadManager: DownloadManager
) : BroadcastReceiver() {


    @SuppressLint("Range")
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "android.intent.action.DOWNLOAD_COMPLETE") {

            val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
            if (downloadId != -1L) {
                println("Download with ID $downloadId finished")
                extract()
            }
        /*    val downloadManager =
                context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val query = DownloadManager.Query().setFilterById(downloadId)
            val cursor: Cursor = downloadManager.query(query)

            if (cursor.moveToFirst()) {
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                if (status == DownloadManager.STATUS_SUCCESSFUL) {
                    val uriString =
                        cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
                    val fileUri = Uri.parse(uriString)

                    val zipFile = File(fileUri.path!!)
                    val targetDirectory = File(context.filesDir, "appAnimation_file.zip")

                    unzipFile(zipFile, targetDirectory)
                }
            }
            cursor.close()*/
        }
    }

    fun extract() {
        val zipFile = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "appAnimation_file.zip"
        )
        val targetDirectory = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "app_animation_folder"
        )

        if (zipFile.exists()) {
            println("File found: ${zipFile.absolutePath}")
            unzipFile(zipFile, targetDirectory)
        } else {
            println("File not found")
        }
    }
    fun unzipFile(zipFile: File, targetDirectory: File) {
        ZipInputStream(FileInputStream(zipFile)).use { zipInputStream ->
            var zipEntry: ZipEntry? = zipInputStream.nextEntry
            while (zipEntry != null) {
                val outputFile = File(targetDirectory, zipEntry.name)
                if (zipEntry.isDirectory) {
                    outputFile.mkdirs()
                } else {
                    outputFile.parentFile?.mkdirs()
                    FileOutputStream(outputFile).use { outputStream ->
                        zipInputStream.copyTo(outputStream)
                    }
                }
                zipInputStream.closeEntry()
                zipEntry = zipInputStream.nextEntry
            }
        }
        println("File found: ${zipFile.absolutePath}")
    }


    //handle fail download
}