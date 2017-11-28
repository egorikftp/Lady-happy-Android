package com.egoriku.ladyhappy.external

import android.os.Bundle

interface AnalyticsInterface {

    fun trackPageView(view: String)

    fun trackGetCategoriesSuccess(bundle: Bundle?)
    fun trackGetCategoriesFail(bundle: Bundle)

    fun trackLoginSuccess(bundle: Bundle)
    fun trackLoginFailure(bundle: Bundle)
    fun trackRegisterSuccess(bundle: Bundle)
    fun trackRegisterFailure(bundle: Bundle)
    fun trackGetTagListSuccess(bundle: Bundle)
    fun trackGetTagListFailure(bundle: Bundle)
    fun trackCreateTaskSuccess(bundle: Bundle)
    fun trackCreateTaskFailure(bundle: Bundle)
    fun trackGetBucketSuccess(bundle: Bundle)
    fun trackGetBucketFailure(bundle: Bundle)
    fun trackDeleteTaskSuccess(bundle: Bundle)
    fun trackDeleteFailure(bundle: Bundle)
}