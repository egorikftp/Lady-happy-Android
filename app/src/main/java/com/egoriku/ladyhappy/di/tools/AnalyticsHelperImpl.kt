package com.egoriku.ladyhappy.di.tools

import android.content.Context
import com.egoriku.core.di.IAnalyticsHelper
import com.egoriku.ladyhappy.firebase.FirebaseAnalyticsHelper
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class AnalyticsHelperImpl
@Inject constructor(context: Context) : IAnalyticsHelper {

    init {
        FirebaseAnalyticsHelper(
                FirebaseAnalytics.getInstance(context)
        )
    }
}