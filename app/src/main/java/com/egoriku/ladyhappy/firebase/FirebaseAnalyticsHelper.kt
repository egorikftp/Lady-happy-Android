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

    override fun trackGetCategoriesSuccess(bundle: Bundle) {
        analytics.logEvent(GET_CATEGORIES_SUCCESS, bundle)
    }

    override fun trackGetCategoriesFail(bundle: Bundle) {
        analytics.logEvent(GET_CATEGORIES_FAIL, bundle)
    }

    override fun trackLoginSuccess(bundle: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun trackLoginFailure(bundle: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun trackRegisterSuccess(bundle: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun trackRegisterFailure(bundle: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun trackGetTagListSuccess(bundle: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun trackGetTagListFailure(bundle: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun trackCreateTaskSuccess(bundle: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun trackCreateTaskFailure(bundle: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun trackGetBucketSuccess(bundle: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun trackGetBucketFailure(bundle: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun trackDeleteTaskSuccess(bundle: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun trackDeleteFailure(bundle: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}