package com.asad.appanimation.home.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.appanimation.home.domain.usecase.FetchHomeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchHomeDataUseCase: FetchHomeDataUseCase
) : ViewModel() {

    init {
        fetchHomeData()
    }

    fun fetchHomeData() {
        viewModelScope.launch {
            fetchHomeDataUseCase.invoke()
                .collect {
                    Log.d(TAG, "fetchHomeData: ${it.value}")
                }
        }
    }
}