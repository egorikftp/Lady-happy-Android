package com.egoriku.ladyhappy.core

import android.view.View
import com.egoriku.ladyhappy.navigation.command.NavigationType
import com.egoriku.ladyhappy.navigation.command.NavigationType.DEFAULT
import com.egoriku.ladyhappy.navigation.screen.Screen

interface IRouter {

    fun replaceScreen(
        screen: Screen,
        navigationType: NavigationType = DEFAULT,
        vararg sharedElements: Pair<View, String>
    )

    fun addScreen(screen: Screen)

    fun addScreenFullscreen(screen: Screen)

    fun back()
}