package com.egoriku.landingfragment.presentation

import android.util.Log
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.models.ILandingModel
import com.egoriku.core.usecase.DefaultObserver
import com.egoriku.core.usecase.Params
import com.egoriku.corelib_kt.arch.BasePresenter
import com.egoriku.landingfragment.domain.interactors.LandingUseCase
import javax.inject.Inject

internal class LandingPagePresenter
@Inject constructor(private val analyticsHelper: IAnalyticsHelper, private val landingUseCase: LandingUseCase)
    : BasePresenter<LandingPageContract.View>(), LandingPageContract.Presenter {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun loadLandingData() {
        if (isViewAttached) {
            view.showLoading()
        }

        landingUseCase.execute(object : DefaultObserver<ILandingModel>() {
            override fun onNext(model: ILandingModel) {
                if (isViewAttached) {
                    view.hideLoading()
                    view.showInformation(model)
                }
            }

            override fun onError(exception: Throwable) {
                if (isViewAttached) {
                    view.hideLoading()
                }
                Log.e(this@LandingPagePresenter.javaClass.simpleName, "Error", exception)
            }
        }, Params.EMPTY)
    }
}