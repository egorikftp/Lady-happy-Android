package com.egoriku.core.di.utils

import ru.terrakok.cicerone.Screen

interface IRouter {

    fun navigateTo(screen: Screen)

    fun newRootScreen(screen: Screen)

    fun replaceScreen(screen: Screen)

    fun exit()
}