package com.egoriku.core

interface IAnalytics {

    fun trackPageView(view: String)

    fun trackNoInternetLanding()
    fun trackNoInternetPhotoReports()

    fun inAppUpdateCanceled()
    fun inAppUpdateFailed()
    fun inAppUpdateSuccess()
}