package com.egoriku.landing.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egoriku.ladyhappy.core.IAnalytics
import com.egoriku.extensions.logE
import com.egoriku.landing.domain.model.LandingModel
import com.egoriku.landing.domain.usecase.LandingUseCase
import com.egoriku.network.ResultOf
import com.egoriku.network.exception.FirestoreNetworkException
import com.egoriku.network.exception.FirestoreParseException
import com.egoriku.network.exception.NoSuchDocumentException
import kotlinx.coroutines.launch

class LandingViewModel(
        private val analytics: IAnalytics,
        private val landingUseCase: LandingUseCase
) : ViewModel() {

    private val screenData = MutableLiveData<LandingScreenModel>()

    val screenState: LiveData<LandingScreenModel> = screenData

    init {
        getLandingData()
    }

    private fun getLandingData() {
        viewModelScope.launch {
            processResult(LoadState.PROGRESS)

            when (val resultOf = landingUseCase.getLandingInfo()) {
                is ResultOf.Success -> processResult(LoadState.NONE, resultOf.value)

                is ResultOf.Failure -> {
                    when (resultOf.throwable) {
                        is FirestoreNetworkException -> {
                            logE("FirestoreNetworkException", resultOf.throwable)
                            analytics.trackNoInternetLanding()
                        }
                        is FirestoreParseException -> logE("FirestoreParseException", resultOf.throwable)
                        is NoSuchDocumentException -> logE("NoSuchDocumentException", resultOf.throwable)
                    }

                    processResult(LoadState.ERROR_LOADING)
                }
            }
        }
    }

    fun retryLoading() = getLandingData()

    private fun processResult(loadState: LoadState = LoadState.NONE, model: LandingModel? = null) {
        screenData.value = LandingScreenModel(
                loadState = loadState,
                landingModel = model
        )
    }
}