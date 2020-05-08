package com.egoriku.ladyhappy.tools

import android.content.Context
import com.egoriku.core.di.utils.IAnalytics
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class Analytics
@Inject constructor(context: Context) : IAnalytics {

    companion object {
        const val ERROR_NO_INTERNET_LANDING = "error_no_internet_landing"
        const val ERROR_NO_INTERNET_PHOTO_REPORTS = "error_no_internet_photo_reports"

        const val IN_APP_UPDATE_CANCELED = "in_app_update_canceled"
        const val IN_APP_UPDATE_FAILED = "in_app_update_failed"
        const val IN_APP_UPDATE_SUCCESS = "in_app_update_success"
    }

    private val analytics: FirebaseAnalytics by lazy { FirebaseAnalytics.getInstance(context) }

    override fun trackPageView(view: String) = analytics.logEvent(view, null)

    override fun trackNoInternetLanding() = analytics.logEvent(ERROR_NO_INTERNET_LANDING, null)

    override fun trackNoInternetPhotoReports() = analytics.logEvent(ERROR_NO_INTERNET_PHOTO_REPORTS, null)

    override fun inAppUpdateCanceled() = analytics.logEvent(IN_APP_UPDATE_CANCELED, null)

    override fun inAppUpdateFailed() = analytics.logEvent(IN_APP_UPDATE_FAILED, null)

    override fun inAppUpdateSuccess() = analytics.logEvent(IN_APP_UPDATE_SUCCESS, null)
}
