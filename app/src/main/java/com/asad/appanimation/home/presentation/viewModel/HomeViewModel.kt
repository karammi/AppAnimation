package com.asad.appanimation.home.presentation.viewModel

import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.appanimation.core.data.dataSource.DataResult
import com.asad.appanimation.home.domain.usecase.DownloadUseCase
import com.asad.appanimation.home.domain.usecase.FetchHomeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchHomeDataUseCase: FetchHomeDataUseCase,
    private val downloadUseCase: DownloadUseCase

) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
//        fetchHomeData()
    }

    init {
        fetchDirectoryItems()
    }

    fun fetchHomeData() {
        viewModelScope.launch {
            fetchHomeDataUseCase.invoke()
                .collect {
                    if (it is DataResult.Success) {
                        downloadUseCase.invoke(it.value.first().url)
                    } else {
                    }
                }
        }
    }

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

//        val files = getImageFilesFromDirectory()

        _uiState.update { currentState ->
            currentState.copy(folders = folders)
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = false,
    val folders: List<File>? = emptyList<File>()
)