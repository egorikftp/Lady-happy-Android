package com.egoriku.ladyhappy.tools

import com.egoriku.core.di.utils.IRouter
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import javax.inject.Inject

class Router
@Inject constructor(private val router: Router) : IRouter {

    override fun navigateTo(screen: Screen) = router.navigateTo(screen)

    override fun newRootScreen(screen: Screen) = router.newRootScreen(screen)

    override fun exit() = router.exit()
}