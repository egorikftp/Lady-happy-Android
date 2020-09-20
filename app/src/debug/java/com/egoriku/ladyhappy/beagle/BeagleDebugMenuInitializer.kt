package com.egoriku.ladyhappy.beagle

import com.egoriku.extensions.activityManager
import com.egoriku.ladyhappy.Application
import com.egoriku.ladyhappy.BuildConfig
import com.egoriku.ladyhappy.R
import com.pandulapeter.beagle.Beagle
import com.pandulapeter.beagle.modules.AppInfoButtonModule
import com.pandulapeter.beagle.modules.ButtonModule
import com.pandulapeter.beagle.modules.DeveloperOptionsButtonModule
import com.pandulapeter.beagle.modules.HeaderModule
import leakcanary.LeakCanary

class BeagleDebugMenuInitializer {

    fun initWith(application: Application) = with(application) {
        registerActivityLifecycleCallbacks(BeagleLifecycleListener())

        Beagle.initialize(application = this)
        Beagle.set(
                HeaderModule(
                        title = getString(R.string.application_name),
                        subtitle = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
                ),
                AppInfoButtonModule(),
                DeveloperOptionsButtonModule(),
                ButtonModule(
                        text = "Clear App Data",
                        onButtonPressed = {
                            application.activityManager.clearApplicationUserData()
                        }
                ),
                ButtonModule(
                        text = "Dump Leaks",
                        onButtonPressed = {
                            LeakCanary.dumpHeap()
                        }
                )
        )
    }
}