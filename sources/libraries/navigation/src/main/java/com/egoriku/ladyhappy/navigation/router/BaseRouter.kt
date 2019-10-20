package com.egoriku.ladyhappy.navigation.router

import com.egoriku.ladyhappy.navigation.command.Command
import com.egoriku.ladyhappy.navigation.navigator.CommandBuffer

open class BaseRouter {

    val commandBuffer: CommandBuffer = CommandBuffer()

    protected fun executeCommands(vararg commands: Command) {
        commandBuffer.executeCommands(commands)
    }
}