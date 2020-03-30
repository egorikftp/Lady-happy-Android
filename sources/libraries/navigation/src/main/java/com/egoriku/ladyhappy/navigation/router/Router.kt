package com.egoriku.ladyhappy.navigation.router

import com.egoriku.ladyhappy.navigation.command.Add
import com.egoriku.ladyhappy.navigation.command.Back
import com.egoriku.ladyhappy.navigation.command.Replace
import com.egoriku.ladyhappy.navigation.screen.Screen

class Router : BaseRouter() {

    fun replaceWith(screen: Screen) {
        executeCommands(Replace(screen = screen))
    }

    fun addScreen(screen: Screen) {
        executeCommands(Add(screen = screen))
    }

    fun addScreenWithContainerId(screen: Screen, id: Int) {
        executeCommands(Add(
                screen = screen,
                containerId = id
        ))
    }

    fun back() {
        executeCommands(Back())
    }
}