package com.egoriku.ladyhappy.navigation.navigator

import com.egoriku.ladyhappy.navigation.command.Command

interface INavigator {

    fun applyCommands(commands: Array<out Command>)
}