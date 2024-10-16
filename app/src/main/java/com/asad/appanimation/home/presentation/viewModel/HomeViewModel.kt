package com.asad.appanimation.home.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.appanimation.core.data.dataSource.DataResult
import com.asad.appanimation.home.domain.usecase.DownloadUseCase
import com.asad.appanimation.home.domain.usecase.FetchHomeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchHomeDataUseCase: FetchHomeDataUseCase,
    private val downloadUseCase: DownloadUseCase

) : ViewModel() {

    init {
        fetchHomeData()
    }

    fun fetchHomeData() {
        viewModelScope.launch {
            fetchHomeDataUseCase.invoke()
                .collect {
                    Log.d(TAG, "fetchHomeData: ${it.value}")
                    if (it is DataResult.Success) {
                        downloadUseCase.invoke(it.value.first().url)
                    } else {

                    }
                }
        }
    }
}