package com.egoriku.giugi

import com.egoriku.corelib_kt.extensions.DelegatesExt
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router


class App : DebugApplication() {
    companion object {
        @JvmStatic
        var instance: App by DelegatesExt.notNullSingleValue()
            private set
        private lateinit var cicerone: Cicerone<Router>
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        cicerone = Cicerone.create()
    }

    fun getNavigationHolder() = cicerone.navigatorHolder

    fun getRouter() = cicerone.router
}
