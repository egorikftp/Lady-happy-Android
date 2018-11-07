package com.egoriku.ladyhappy.tools

import android.content.Context
import android.os.Bundle
import com.egoriku.core.common.TrackingConstants
import com.egoriku.core.di.utils.IAnalytics
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class Analytics
@Inject constructor(context: Context) : IAnalytics {

    companion object {
        const val ERROR_NO_INTERNET_LANDING = "error_no_internet_landing"
    }

    private val analytics: FirebaseAnalytics by lazy { FirebaseAnalytics.getInstance(context) }

    override fun trackPageView(view: String) = analytics.logEvent(view, null)

    override fun trackGetCategoriesSuccess(bundle: Bundle?) =
            analytics.logEvent(TrackingConstants.GET_CATEGORIES_SUCCESS, bundle)

    override fun trackGetCategoriesFail(bundle: Bundle?) =
            analytics.logEvent(TrackingConstants.GET_CATEGORIES_FAIL, bundle)

    override fun trackNoInternetLanding() = analytics.logEvent(ERROR_NO_INTERNET_LANDING, null)
}
