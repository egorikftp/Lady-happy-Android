package com.egoriku.ladyhappy.navigation

import com.egoriku.ladyhappy.navigation.navigator.NavigatorHolder
import com.egoriku.ladyhappy.navigation.router.BaseRouter
import com.egoriku.ladyhappy.navigation.router.Router

class HeyNav<T : BaseRouter> private constructor(val router: T) {

    val navigatorHolder: NavigatorHolder
        get() = router.commandBuffer

    companion object {

        /**
         * Creates the Cicerone instance with the default [router][Router]
         */
        fun create(): HeyNav<Router> {
            return create(Router())
        }

        /**
         * Creates the Cicerone instance with the custom router.
         * @param customRouter the custom router extending [BaseRouter]
         */
        fun <T : BaseRouter> create(customRouter: T): HeyNav<T> {
            return HeyNav(customRouter)
        }
    }
}