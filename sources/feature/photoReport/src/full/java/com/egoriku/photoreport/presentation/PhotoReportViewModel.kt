package com.egoriku.photoreport.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.exception.FirestoreNetworkException
import com.egoriku.core.exception.FirestoreParseException
import com.egoriku.core.exception.NoSuchDocumentException
import com.egoriku.network.ResultOf
import com.egoriku.photoreport.domain.interactor.PhotoReportUseCase
import com.egoriku.photoreport.domain.model.PhotoReportModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PhotoReportViewModel
@Inject constructor(
        private val photoReportUseCase: PhotoReportUseCase,
        private val analytics: IAnalytics
) : ViewModel(), CoroutineScope {

    private val job = Job()
    private val screenData = MutableLiveData<ScreenModel>()

    val screenState: LiveData<ScreenModel> = screenData

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        getPhotoReportData()
    }

    private fun getPhotoReportData() {
        launch {
            processResult(LoadState.PROGRESS)

            val resultOf: ResultOf<List<PhotoReportModel>> = withContext(Dispatchers.IO) {
                photoReportUseCase.getPhotoReportInfo()
            }

            when (resultOf) {
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

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}