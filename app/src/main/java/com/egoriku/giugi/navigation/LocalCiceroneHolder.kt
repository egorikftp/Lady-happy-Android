package com.egoriku.giugi.navigation

import android.support.v4.util.ArrayMap
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class LocalCiceroneHolder {

    private var container: ArrayMap<String, Cicerone<Router>> = ArrayMap()

    fun getCicerone(containerTag: String): Cicerone<Router>? {

        if (!container.containsKey(containerTag)) {
            container.put(containerTag, Cicerone.create())
        }

        return container[containerTag]
    }
}
