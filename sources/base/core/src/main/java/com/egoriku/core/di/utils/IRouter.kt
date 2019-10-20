package com.egoriku.core.di.utils

import com.egoriku.ladyhappy.navigation.screen.Screen

interface IRouter {

    fun replaceScreen(screen: Screen)

    fun back()
}