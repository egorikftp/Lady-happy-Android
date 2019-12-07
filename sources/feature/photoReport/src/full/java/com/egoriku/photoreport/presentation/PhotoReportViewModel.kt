package com.egoriku.photoreport.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.exception.FirestoreNetworkException
import com.egoriku.core.exception.FirestoreParseException
import com.egoriku.core.exception.NoSuchDocumentException
import com.egoriku.network.Result
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

            val result: Result<List<PhotoReportModel>> = withContext(Dispatchers.IO) {
                photoReportUseCase.getPhotoReportInfo()
            }

            when (result) {
                is Result.Success -> processResult(LoadState.NONE, result.value)

                is Result.Error -> {
                    when (result.exception) {
                        is FirestoreNetworkException -> {
                            Log.e("PhotoReportPresenter", "FirestoreNetworkException", result.exception)
                            analytics.trackNoInternetPhotoReports()
                        }
                        is FirestoreParseException -> Log.e("PhotoReportPresenter", "FirestoreParseException", result.exception)
                        is NoSuchDocumentException -> Log.e("PhotoReportPresenter", "NoSuchDocumentException", result.exception)
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