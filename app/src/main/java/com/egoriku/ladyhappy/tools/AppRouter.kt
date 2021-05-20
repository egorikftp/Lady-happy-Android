package com.egoriku.ladyhappy.tools

import android.view.View
import com.egoriku.ladyhappy.core.IRouter
import com.egoriku.ladyhappy.navigation.command.NavigationType
import com.egoriku.ladyhappy.navigation.router.Router
import com.egoriku.ladyhappy.navigation.screen.Screen

internal class AppRouter(
    private val router: Router
) : IRouter {

    override fun replaceScreen(
        screen: Screen,
        navigationType: NavigationType,
        vararg sharedElements: Pair<View, String>
    ) = router.replaceScreen(
        screen = screen,
        navigationType = navigationType,
        sharedElements = sharedElements
    )

    override fun addScreen(screen: Screen) = router.addScreen(screen)

    override fun addScreenFullscreen(screen: Screen) = router.addScreenFullscreen(screen)

    override fun back() = router.back()
}