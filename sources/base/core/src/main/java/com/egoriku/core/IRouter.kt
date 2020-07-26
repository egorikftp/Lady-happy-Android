package com.egoriku.core

import com.egoriku.ladyhappy.navigation.screen.Screen

interface IRouter {

    fun replaceScreen(screen: Screen)

    fun addScreen(screen: Screen)

    fun addScreenWithContainerId(screen: Screen, id: Int)

    fun back()
}