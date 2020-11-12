package com.egoriku.ladyhappy.beagle

import com.egoriku.ladyhappy.Application
import com.egoriku.ladyhappy.BuildConfig
import com.egoriku.ladyhappy.R
import com.egoriku.ladyhappy.extensions.activityManager
import com.pandulapeter.beagle.Beagle
import com.pandulapeter.beagle.common.configuration.Behavior
import com.pandulapeter.beagle.modules.AppInfoButtonModule
import com.pandulapeter.beagle.modules.DeveloperOptionsButtonModule
import com.pandulapeter.beagle.modules.HeaderModule
import com.pandulapeter.beagle.modules.TextModule
import leakcanary.LeakCanary

class BeagleDebugMenuInitializer {

    fun initWith(application: Application) = with(application) {
        registerActivityLifecycleCallbacks(BeagleLifecycleListener())

        Beagle.initialize(
                application = this,
                behavior = Behavior(bugReportingBehavior = Behavior.BugReportingBehavior(shouldCatchExceptions = false))
        )
        Beagle.set(
                HeaderModule(
                        title = getString(R.string.application_name),
                        subtitle = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
                ),
                AppInfoButtonModule(),
                DeveloperOptionsButtonModule(),
                TextModule(
                        type = TextModule.Type.BUTTON,
                        text = "Clear App Data",
                        onItemSelected = {
                            application.activityManager.clearApplicationUserData()
                        }
                ),
                TextModule(
                        type = TextModule.Type.BUTTON,
                        text = "Dump Leaks",
                        onItemSelected = {
                            LeakCanary.dumpHeap()
                        }
                )
        )
    }
}