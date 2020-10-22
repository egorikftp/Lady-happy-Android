package com.egoriku.ladyhappy.core

import android.view.View
import com.egoriku.ladyhappy.navigation.screen.Screen

interface IRouter {

    fun replaceScreen(screen: Screen, vararg sharedElements: Pair<View, String>)

    fun addScreen(screen: Screen)

    fun addScreenFullscreen(screen: Screen)

    fun back()
}