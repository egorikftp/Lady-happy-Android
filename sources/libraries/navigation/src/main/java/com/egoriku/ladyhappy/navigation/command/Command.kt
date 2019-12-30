package com.egoriku.ladyhappy.navigation.command

import androidx.annotation.IdRes
import com.egoriku.ladyhappy.navigation.screen.Screen

sealed class Command

class Replace(val screen: Screen) : Command()

class Add(
        val screen: Screen,

        @IdRes
        val containerId: Int = 0
) : Command()

class Back : Command()