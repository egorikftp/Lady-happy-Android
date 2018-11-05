package com.egoriku.landingfragment.presentation

import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.model.ILandingModel
import com.egoriku.core.usecase.AppObserver
import com.egoriku.core.usecase.Params
import com.egoriku.landingfragment.domain.interactors.LandingUseCase
import com.egoriku.ui.arch.pvm.BasePresenter
import javax.inject.Inject

internal class LandingPagePresenter
@Inject constructor(private val analyticsHelper: IAnalyticsHelper, private val landingUseCase: LandingUseCase)
    : BasePresenter<LandingPageContract.View>(), LandingPageContract.Presenter {

    private var screenModel: LandingScreenModel = LandingScreenModel()

    override fun loadLandingData() {
        when {
            !screenModel.isEmpty() -> view?.render(screenModel)
            else -> getLandingData()
        }
    }

    override fun retryLoading() = getLandingData()

    private fun getLandingData() {
        processResult(LoadState.PROGRESS)

        landingUseCase.execute(object : AppObserver<ILandingModel>() {
            override fun onNext(model: ILandingModel) = processResult(LoadState.NONE, model)

            override fun onError(exception: Throwable) = processResult(LoadState.ERROR_LOADING)
        }, Params.EMPTY)
    }

    private fun processResult(loadState: LoadState = LoadState.NONE, model: ILandingModel? = null) {
        screenModel.let {
            it.landingModel = model
            it.loadState = loadState

            view?.render(it)
        }
    }
}