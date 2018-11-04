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
            else -> {
                view?.showLoading()
                getLandingData()
            }
        }
    }

    override fun retryLoading() = getLandingData()

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    private fun getLandingData() {
        landingUseCase.execute(object : AppObserver<ILandingModel>() {
            override fun onNext(model: ILandingModel) = processResult(model)

            override fun onError(exception: Throwable) = processResult()
        }, Params.EMPTY)
    }

    private fun processResult(model: ILandingModel? = null) {
        screenModel.landingModel = model

        view?.let {
            it.hideLoading()
            it.render(screenModel)
        }
    }
}