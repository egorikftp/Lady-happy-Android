package com.egoriku.ladyhappy

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.egoriku.core.actions.common.IMainActivityConnector
import net.hockeyapp.android.UpdateManager

object DebugInitializer {

    fun register(application: Application) {
        application.registerActivityLifecycleCallbacks(object : SimpleActivityLifecycleCallback() {

            override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
                if (activity is IMainActivityConnector && BuildConfig.DEBUG) {
                    UpdateManager.register(activity)
                }
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (BuildConfig.DEBUG && activity is IMainActivityConnector) {
                    UpdateManager.unregister()
                }
            }
        })
    }
}
