package com.egoriku.core.di.utils

import android.os.Bundle

interface IAnalytics {

    fun trackPageView(view: String)

    fun trackGetCategoriesSuccess(bundle: Bundle?)
    fun trackGetCategoriesFail(bundle: Bundle?)

    fun trackNoInternetLanding()
}