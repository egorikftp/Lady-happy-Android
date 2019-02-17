package com.egoriku.ladyhappy.featureprovider.registry

import android.app.Activity
import com.egoriku.ladyhappy.featureprovider.AvailableFeatures

internal class ActivityRegistry : ClassRegistry() {

    companion object {

        fun findMainScreen(): Class<out Activity> = findActivityClass(AvailableFeatures.MAIN_SCREEN)
    }
}