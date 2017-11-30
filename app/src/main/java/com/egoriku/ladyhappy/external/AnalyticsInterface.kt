package com.egoriku.ladyhappy.external

import android.os.Bundle

interface AnalyticsInterface {

    fun trackPageView(view: String)

    fun trackGetCategoriesSuccess(bundle: Bundle?)
    fun trackGetCategoriesFail(bundle: Bundle)
}