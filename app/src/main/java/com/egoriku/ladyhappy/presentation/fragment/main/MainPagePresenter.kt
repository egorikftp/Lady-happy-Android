package com.egoriku.ladyhappy.presentation.fragment.main

import com.egoriku.corelib_kt.arch.BasePresenter
import com.egoriku.ladyhappy.external.AnalyticsInterface
import javax.inject.Inject

class MainPagePresenter
@Inject constructor(private val analyticsInterface: AnalyticsInterface)
    : BasePresenter<MainPageContract.View>(), MainPageContract.Presenter {


}