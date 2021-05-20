package com.egoriku.ladyhappy.tools

import android.content.Context
import com.egoriku.ladyhappy.core.IAnalytics
import com.google.firebase.analytics.FirebaseAnalytics

internal class Analytics(context: Context) : IAnalytics {

    private val firebaseAnalytics: FirebaseAnalytics by lazy { FirebaseAnalytics.getInstance(context) }

    override fun trackPageView(view: String) = firebaseAnalytics.logEvent(view, null)

    override fun trackNoInternetLanding() =
        firebaseAnalytics.logEvent(ERROR_NO_INTERNET_LANDING, null)

    override fun trackNoInternetPhotoReports() =
        firebaseAnalytics.logEvent(ERROR_NO_INTERNET_PHOTO_REPORTS, null)

    override fun inAppUpdateCanceled() = firebaseAnalytics.logEvent(IN_APP_UPDATE_CANCELED, null)

    override fun inAppUpdateFailed() = firebaseAnalytics.logEvent(IN_APP_UPDATE_FAILED, null)

    override fun inAppUpdateSuccess() = firebaseAnalytics.logEvent(IN_APP_UPDATE_SUCCESS, null)

    companion object {
        const val ERROR_NO_INTERNET_LANDING = "error_no_internet_landing"
        const val ERROR_NO_INTERNET_PHOTO_REPORTS = "error_no_internet_photo_reports"

        const val IN_APP_UPDATE_CANCELED = "in_app_update_canceled"
        const val IN_APP_UPDATE_FAILED = "in_app_update_failed"
        const val IN_APP_UPDATE_SUCCESS = "in_app_update_success"
    }
}
