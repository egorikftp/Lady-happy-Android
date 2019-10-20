package com.egoriku.ladyhappy.navigation.router

import com.egoriku.ladyhappy.navigation.command.Back
import com.egoriku.ladyhappy.navigation.command.Replace
import com.egoriku.ladyhappy.navigation.screen.Screen

class Router : BaseRouter() {

    fun replaceWith(screen: Screen) {
        executeCommands(Replace(screen))
    }

    fun back() {
        executeCommands(Back())
    }
}