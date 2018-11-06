package com.egoriku.landingfragment.presentation

import android.util.Log
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.core.model.ILandingModel
import com.egoriku.core.usecase.AppObserver
import com.egoriku.core.usecase.Params
import com.egoriku.landingfragment.domain.interactors.LandingUseCase
import com.egoriku.ui.arch.pvm.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class LandingPagePresenter
@Inject constructor(private val analyticsHelper: IAnalyticsHelper, private val landingUseCase: LandingUseCase)
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

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    private fun getLandingData() {
        launch {
        }

        landingUseCase.execute(object : AppObserver<ILandingModel>() {
            override fun onNext(model: ILandingModel) {
                screenModel.landingModel = model

                view?.let {
                    it.hideLoading()
                    it.render(screenModel)
                }
            }

            override fun onError(exception: Throwable) {
                screenModel.landingModel = null

                view?.hideLoading()

                Log.e(this@LandingPagePresenter.javaClass.simpleName, "Error", exception)
            }
        }, Params.EMPTY)
    }
}