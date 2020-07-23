package com.egoriku.ladyhappy.landing.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.exception.FirestoreNetworkException
import com.egoriku.core.exception.FirestoreParseException
import com.egoriku.core.exception.NoSuchDocumentException
import com.egoriku.ladyhappy.landing.domain.interactors.LandingUseCase
import com.egoriku.ladyhappy.landing.domain.model.LandingModel
import com.egoriku.network.ResultOf
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
            val resultOf: ResultOf<LandingModel> = withContext(Dispatchers.IO) {
                landingUseCase.getLandingInfo()
            }

            when (resultOf) {
                is ResultOf.Success -> processResult(LoadState.NONE, resultOf.value)

                is ResultOf.Failure -> {
                    when (resultOf.throwable) {
                        is FirestoreNetworkException -> {
                            Log.e("LandingPagePresenter", "FirestoreNetworkException", resultOf.throwable)
                            analytics.trackNoInternetLanding()
                        }
                        is FirestoreParseException -> Log.e("LandingPagePresenter", "FirestoreParseException", resultOf.throwable)
                        is NoSuchDocumentException -> Log.e("LandingPagePresenter", "NoSuchDocumentException", resultOf.throwable)
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