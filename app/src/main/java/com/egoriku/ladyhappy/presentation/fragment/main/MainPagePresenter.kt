package com.egoriku.ladyhappy.presentation.fragment.main

import com.egoriku.core.di.utils.IAnalyticsHelper
import com.egoriku.corelib_kt.arch.BasePresenter
import javax.inject.Inject

class MainPagePresenter
@Inject constructor(private val analyticsHelper: IAnalyticsHelper)
    : BasePresenter<MainPageContract.View>(), MainPageContract.Presenter {


}