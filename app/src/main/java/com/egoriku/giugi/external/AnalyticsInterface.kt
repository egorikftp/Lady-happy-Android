package com.egoriku.giugi.external

import android.os.Bundle

interface AnalyticsInterface {
    val PARAM_USER_UID: String
        get() = "user_uid"
    val PARAM_MESSAGE: String
        get() = "message"

    val VIEW_ONBOARDING: String
        get() = "view_onboarding"

    val VIEW_LOGIN: String
        get() = "view_login"

    val VIEW_REGISTRATION: String
        get() = "view_register"

    public val VIEW_BUCKET: String
        get() = "view_bucket"

    val VIEW_CREATE_TASK: String
        get() = "view_create_task"

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

    fun trackPageView(view: String)
}