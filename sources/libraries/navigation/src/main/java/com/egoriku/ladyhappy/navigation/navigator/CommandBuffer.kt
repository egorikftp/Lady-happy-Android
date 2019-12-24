package com.egoriku.ladyhappy.navigation.navigator

import com.egoriku.ladyhappy.navigation.command.Command
import java.util.*

class CommandBuffer : NavigatorHolder {

    private var navigator: INavigator? = null

    private val pendingCommands: Queue<Array<out Command>> = LinkedList()

    override fun setNavigator(navigator: INavigator) {
        this.navigator = navigator

        while (!pendingCommands.isEmpty()) {
            executeCommands(pendingCommands.poll())
        }
    }

    override fun removeNavigator() {
        navigator = null
    }

    fun executeCommands(commands: Array<out Command>) {
        val navigator = navigator

        if (navigator != null) {
            navigator.applyCommands(commands)
        } else {
            pendingCommands.add(commands)
        }
    }
}
