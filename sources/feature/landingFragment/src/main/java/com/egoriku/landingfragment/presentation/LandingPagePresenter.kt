package com.egoriku.landingfragment.presentation

import android.util.Log
import com.egoriku.core.di.utils.IAnalytics
import com.egoriku.core.exception.FirestoreNetworkException
import com.egoriku.core.exception.FirestoreParseException
import com.egoriku.core.exception.NoSuchDocumentException
import com.egoriku.core.firestore.Result
import com.egoriku.core.model.ILandingModel
import com.egoriku.landingfragment.domain.interactors.LandingUseCase
import com.egoriku.ui.arch.pvm.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class LandingPagePresenter
@Inject constructor(private val analytics: IAnalytics,
                    private val landingUseCase: LandingUseCase)
    : BasePresenter<LandingPageContract.View>(), LandingPageContract.Presenter, CoroutineScope {

    private var screenModel: LandingScreenModel = LandingScreenModel()
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    override fun loadLandingData() {
        when {
            !screenModel.isEmpty() -> view?.render(screenModel)
            else -> {
                view?.showLoading()
                getLandingData()
            }
        }
    }

    private fun getLandingData() {
        launch {
            val result: Result<ILandingModel> = landingUseCase.getLandingInfo()

            when (result) {
                is Result.Success -> {
                    screenModel.landingModel = result.value

                    view?.let {
                        it.hideLoading()
                        it.render(screenModel)
                    }
                }

                is Result.Error -> {
                    when (result.exception) {
                        is FirestoreNetworkException -> {
                            Log.e("LandingPagePresenter", "FirestoreNetworkException", result.exception)
                            analytics.trackNoInternetLanding()
                        }
                        is FirestoreParseException -> Log.e("LandingPagePresenter", "FirestoreParseException", result.exception)
                        is NoSuchDocumentException -> Log.e("LandingPagePresenter", "NoSuchDocumentException", result.exception)
                    }

                    view?.hideLoading()
                }
            }
        }
    }

    override fun detachView() {
        job.cancel()
        super.detachView()
    }
}