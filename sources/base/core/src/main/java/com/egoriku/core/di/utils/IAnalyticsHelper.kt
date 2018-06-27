package com.egoriku.core.di.utils

import android.os.Bundle

interface IAnalyticsHelper {

    fun trackPageView(view: String)

    fun trackGetCategoriesSuccess(bundle: Bundle?)
    fun trackGetCategoriesFail(bundle: Bundle?)
}