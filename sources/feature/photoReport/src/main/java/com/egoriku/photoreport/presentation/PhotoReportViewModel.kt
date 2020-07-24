package com.egoriku.photoreport.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.core.IAnalytics
import com.egoriku.network.ResultOf
import com.egoriku.network.exception.FirestoreNetworkException
import com.egoriku.network.exception.FirestoreParseException
import com.egoriku.network.exception.NoSuchDocumentException
import com.egoriku.photoreport.domain.model.PhotoReportModel
import com.egoriku.photoreport.domain.usecase.PhotoReportUseCase
import kotlinx.coroutines.launch

class PhotoReportViewModel(
        private val photoReportUseCase: PhotoReportUseCase,
        private val analytics: IAnalytics
) : ViewModel() {

    private val screenData = MutableLiveData<ScreenModel>()

    val screenState: LiveData<ScreenModel> = screenData

    init {
        getPhotoReportData()
    }

    private fun getPhotoReportData() {
        viewModelScope.launch {
            processResult(LoadState.PROGRESS)

            when (val resultOf = photoReportUseCase.getPhotoReportInfo()) {
                is ResultOf.Success -> processResult(LoadState.NONE, resultOf.value)

                is ResultOf.Failure -> {
                    when (resultOf.throwable) {
                        is FirestoreNetworkException -> {
                            Log.e("PhotoReportPresenter", "FirestoreNetworkException", resultOf.throwable)
                            analytics.trackNoInternetPhotoReports()
                        }
                        is FirestoreParseException -> Log.e("PhotoReportPresenter", "FirestoreParseException", resultOf.throwable)
                        is NoSuchDocumentException -> Log.e("PhotoReportPresenter", "NoSuchDocumentException", resultOf.throwable)
                    }

                    processResult(LoadState.ERROR_LOADING)
                }
            }
        }
    }

    private fun processResult(loadState: LoadState = LoadState.NONE, model: List<PhotoReportModel>? = null) {
        screenData.value = ScreenModel(
                photoReports = model,
                loadState = loadState
        )
    }

    fun retryLoading() = getPhotoReportData()
}