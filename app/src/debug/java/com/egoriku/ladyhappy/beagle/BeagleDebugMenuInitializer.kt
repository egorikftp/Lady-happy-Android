package com.egoriku.ladyhappy.beagle

import com.egoriku.ladyhappy.Application
import com.egoriku.ladyhappy.BuildConfig
import com.egoriku.ladyhappy.R
import com.pandulapeter.beagle.Beagle
import com.pandulapeter.beagleCore.configuration.Trick
import leakcanary.LeakCanary

class BeagleDebugMenuInitializer {

    fun initWith(application: Application) = with(application) {
        registerActivityLifecycleCallbacks(BeagleLifecycleListener())

        Beagle.imprint(this)
        Beagle.learn(
                listOf(
                        Trick.Text(
                                id = "stub",
                                text = ""
                        ),
                        Trick.Text(
                                text = getString(R.string.application_name),
                                isTitle = true
                        ),
                        Trick.Text(
                                text = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
                        ),
                        Trick.AppInfoButton(),
                        Trick.Button(
                                text = "Dump Leaks",
                                onButtonPressed = {
                                    LeakCanary.dumpHeap()
                                }
                        )
                )
        )
    }
}