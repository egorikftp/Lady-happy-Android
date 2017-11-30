package com.egoriku.ladyhappy.firebase

import android.os.Bundle
import com.egoriku.ladyhappy.external.AnalyticsInterface
import com.egoriku.ladyhappy.external.TrackingConstants.Companion.GET_CATEGORIES_FAIL
import com.egoriku.ladyhappy.external.TrackingConstants.Companion.GET_CATEGORIES_SUCCESS
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsHelper(private val analytics: FirebaseAnalytics) : AnalyticsInterface {

    override fun trackPageView(view: String) {
        analytics.logEvent(view, null)
    }

    override fun trackGetCategoriesSuccess(bundle: Bundle?) {
        analytics.logEvent(GET_CATEGORIES_SUCCESS, bundle)
    }

    override fun trackGetCategoriesFail(bundle: Bundle) {
        analytics.logEvent(GET_CATEGORIES_FAIL, bundle)
    }
}