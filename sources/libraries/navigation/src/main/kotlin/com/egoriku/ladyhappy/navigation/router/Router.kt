package com.egoriku.ladyhappy.navigation.router

import android.view.View
import com.egoriku.ladyhappy.navigation.command.Command.*
import com.egoriku.ladyhappy.navigation.command.NavigationType
import com.egoriku.ladyhappy.navigation.command.NavigationType.DEFAULT
import com.egoriku.ladyhappy.navigation.screen.Screen

class Router : BaseRouter() {

    fun replaceScreen(
        screen: Screen,
        navigationType: NavigationType = DEFAULT,
        vararg sharedElements: Pair<View, String>
    ) {
        executeCommands(
            Replace(
                navigationType = navigationType,
                screen = screen,
                sharedElements = sharedElements.toMap()
            )
        )
    }

    fun addScreen(screen: Screen) {
        executeCommands(Add(screen = screen))
    }

    fun addScreenFullscreen(screen: Screen) {
        executeCommands(
            Add(
                screen = screen,
                navigationType = NavigationType.FULLSCREEN
            )
        )
    }

    fun back() = executeCommands(Back)
}