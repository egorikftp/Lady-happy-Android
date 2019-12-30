package com.egoriku.ladyhappy

import com.pandulapeter.beagle.Beagle
import com.pandulapeter.beagleCore.configuration.Trick

class BeagleDebugMenuInitializer {

    fun initWith(application: Application) = with(application) {
        Beagle.imprint(this)
        Beagle.learn(
                listOf(
                        Trick.Header(
                                title = getString(R.string.application_name),
                                subtitle = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
                        ),
                        Trick.AppInfoButton(),
                        Trick.ScreenshotButton()
                )
        )
    }
}