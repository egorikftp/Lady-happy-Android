package com.egoriku.ladyhappy.tools

import com.egoriku.core.di.utils.IRouter
import com.egoriku.ladyhappy.navigation.router.Router
import com.egoriku.ladyhappy.navigation.screen.Screen

import javax.inject.Inject

class Router
@Inject constructor(private val router: Router) : IRouter {

    override fun replaceScreen(screen: Screen) = router.replaceWith(screen)

    override fun addScreen(screen: Screen) = router.addScreen(screen)

    override fun back() = router.back()
}