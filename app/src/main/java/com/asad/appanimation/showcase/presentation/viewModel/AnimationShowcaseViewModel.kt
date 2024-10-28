package com.asad.appanimation.showcase.presentation.viewModel

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.asad.appanimation.app.navigation.NavConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
private const val TAG = "AnimationShowcase"

@HiltViewModel
class AnimationShowcaseViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val path = savedStateHandle.get<String>(NavConstants.ANIMATION_PATH_ARGUMENT)

    fun fetchDirectoryItems() {
        val directory = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "app_animation_folder"
        )

        val folders =
            directory
                .listFiles()
                ?.filter { it.isDirectory }
                ?.filter { !it.name.contains("__") }


        Log.d(TAG, "fetchDirectoryItems: $folders")

    }
}



class ImageRepository(

    @ApplicationContext private val context: Context
) {

    // Function to get a list of image paths from local storage
    fun getLocalImages(): List<File> {
        val imageDir = File(context.filesDir, "images") // Define your image directory
        return imageDir.listFiles()?.filter { it.extension == "png" } ?: emptyList()
    }
}