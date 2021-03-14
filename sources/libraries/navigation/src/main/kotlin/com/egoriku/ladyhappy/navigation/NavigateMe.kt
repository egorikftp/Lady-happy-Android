package com.egoriku.ladyhappy.navigation

import com.egoriku.ladyhappy.navigation.navigator.NavigatorHolder
import com.egoriku.ladyhappy.navigation.router.BaseRouter
import com.egoriku.ladyhappy.navigation.router.Router

class NavigateMe<T : BaseRouter> private constructor(val router: T) {

    val navigatorHolder: NavigatorHolder
        get() = router.commandBuffer

    companion object {

        fun create(): NavigateMe<Router> = create(Router())

        fun <T : BaseRouter> create(customRouter: T) = NavigateMe(customRouter)
    }
}