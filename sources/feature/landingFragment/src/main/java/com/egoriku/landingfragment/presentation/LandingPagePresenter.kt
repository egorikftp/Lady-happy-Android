package com.egoriku.landingfragment.presentation

import android.util.Log
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

    private var screenModel: ILandingModel? = null

    override fun loadLandingData() {
        when {
            screenModel != null -> screenModel?.let { view?.render(it) }
            else -> {
                view?.showLoading()
                getLandingData()
            }
        }
    }

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    private fun getLandingData() {
        landingUseCase.execute(object : AppObserver<ILandingModel>() {
            override fun onNext(model: ILandingModel) {
                screenModel = model

                view?.let {
                    it.hideLoading()
                    it.render(model)
                }
            }

            override fun onError(exception: Throwable) {
                screenModel = null

                view?.hideLoading()

                Log.e(this@LandingPagePresenter.javaClass.simpleName, "Error", exception)
            }
        }, Params.EMPTY)
    }
}