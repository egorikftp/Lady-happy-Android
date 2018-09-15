package com.egoriku.ladyhappy.di.tools

import android.content.Context
import android.os.Bundle
import com.egoriku.core.common.TrackingConstants
import com.egoriku.core.di.utils.IAnalyticsHelper
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class AnalyticsHelper
@Inject constructor(context: Context) : IAnalyticsHelper {

    private val firebaseAnalytics: FirebaseAnalytics by lazy { FirebaseAnalytics.getInstance(context) }

    override fun trackPageView(view: String) = firebaseAnalytics.logEvent(view, null)

    override fun trackGetCategoriesSuccess(bundle: Bundle?) =
            firebaseAnalytics.logEvent(TrackingConstants.GET_CATEGORIES_SUCCESS, bundle)

    override fun trackGetCategoriesFail(bundle: Bundle?) =
            firebaseAnalytics.logEvent(TrackingConstants.GET_CATEGORIES_FAIL, bundle)
}