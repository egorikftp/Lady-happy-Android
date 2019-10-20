package com.egoriku.landing.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.exception.FirestoreNetworkException
import com.egoriku.core.exception.FirestoreParseException
import com.egoriku.core.exception.NoSuchDocumentException
import com.egoriku.landing.domain.interactors.LandingUseCase
import com.egoriku.landing.domain.model.LandingModel
import com.egoriku.network.Result
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LandingViewModel
@Inject constructor(
        private val analytics: IAnalytics,
        private val landingUseCase: LandingUseCase
): ViewModel(), CoroutineScope {

    private val job = Job()
    private val screenData = MutableLiveData<LandingScreenModel>()

    val screenState: LiveData<LandingScreenModel> = screenData

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        getLandingData()
    }

    private fun getLandingData() {
        launch {
            processResult(LoadState.PROGRESS)

            //TODO move Dispatcher into usecase or repository
            val result: Result<LandingModel> = withContext(Dispatchers.IO) {
                landingUseCase.getLandingInfo()
            }

            when (result) {
                is Result.Success -> processResult(LoadState.NONE, result.value)

                is Result.Error -> {
                    when (result.exception) {
                        is FirestoreNetworkException -> {
                            Log.e("LandingPagePresenter", "FirestoreNetworkException", result.exception)
                            analytics.trackNoInternetLanding()
                        }
                        is FirestoreParseException -> Log.e("LandingPagePresenter", "FirestoreParseException", result.exception)
                        is NoSuchDocumentException -> Log.e("LandingPagePresenter", "NoSuchDocumentException", result.exception)
                    }

                    processResult(LoadState.ERROR_LOADING)
                }
            }
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

    fun retryLoading() = getLandingData()

    private fun processResult(loadState: LoadState = LoadState.NONE, model: LandingModel? = null) {
        screenData.value = LandingScreenModel(
                loadState = loadState,
                landingModel = model
        )
    }
}