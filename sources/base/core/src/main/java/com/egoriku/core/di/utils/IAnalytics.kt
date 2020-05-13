package com.egoriku.core.di.utils

interface IAnalytics {

    fun trackPageView(view: String)

    fun trackNoInternetLanding()
    fun trackNoInternetPhotoReports()

    fun inAppUpdateCanceled()
    fun inAppUpdateFailed()
    fun inAppUpdateSuccess()
}