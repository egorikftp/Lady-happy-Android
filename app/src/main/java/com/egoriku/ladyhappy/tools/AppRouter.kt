package com.egoriku.ladyhappy.tools

import com.egoriku.core.di.utils.IRouter
import com.egoriku.ladyhappy.navigation.router.Router
import com.egoriku.ladyhappy.navigation.screen.Screen

class AppRouter(
        private val router: Router
) : IRouter {

    override fun replaceScreen(screen: Screen) = router.replaceWith(screen)

    override fun addScreen(screen: Screen) = router.addScreen(screen)

    override fun addScreenWithContainerId(screen: Screen, id: Int) = router.addScreenWithContainerId(screen, id)

    override fun back() = router.back()
}