package com.egoriku.mainfragment.presentation.fragment

import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.models.ILandingModel
import com.egoriku.core.usecase.DefaultObserver
import com.egoriku.core.usecase.Params
import com.egoriku.corelib_kt.arch.BasePresenter
import com.egoriku.mainfragment.domain.interactors.LandingUseCase
import javax.inject.Inject

class MainPagePresenter
@Inject constructor(private val analyticsHelper: IAnalyticsHelper, private val landingUseCase: LandingUseCase)
    : BasePresenter<MainPageContract.View>(), MainPageContract.Presenter {

    override fun loadLandingData() {
        landingUseCase.execute(Observer(), Params.EMPTY)
    }

    private inner class Observer : DefaultObserver<ILandingModel>() {

        override fun onComplete() {
            super.onComplete()
        }

        override fun onNext(t: ILandingModel) {
            if (isViewAttached) {
                view.showInformation(t)
            }
        }

        override fun onError(exception: Throwable) {
        }
    }
}