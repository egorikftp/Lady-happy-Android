package com.egoriku.photoreportfragment.presentation

import android.util.Log
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.exception.FirestoreNetworkException
import com.egoriku.core.exception.FirestoreParseException
import com.egoriku.core.exception.NoSuchDocumentException
import com.egoriku.core.firestore.Result
import com.egoriku.photoreportfragment.domain.interactor.PhotoReportUseCase
import com.egoriku.photoreportfragment.domain.model.PhotoReportModel
import com.egoriku.ui.arch.pvm.BasePresenter
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class PhotoReportPresenter
@Inject constructor(
        private val photoReportUseCase: PhotoReportUseCase,
        private val analytics: IAnalytics
) : BasePresenter<PhotoReportContract.View>(), PhotoReportContract.Presenter, CoroutineScope {

    private var screenModel = ScreenModel()
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun loadData() {
        when {
            !screenModel.isEmpty() -> view?.render(screenModel)
            else -> getPhotoReportData()
        }
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
        screenModel.let {
            it.photoReports = model
            it.loadState = loadState

            view?.render(it)
        }
    }

    override fun detachView() {
        job.cancel()
        super.detachView()
    }
}