package com.egoriku.ladyhappy.navigation.command

import android.view.View
import com.egoriku.ladyhappy.navigation.command.NavigationType.DEFAULT
import com.egoriku.ladyhappy.navigation.screen.Screen

sealed class Command {
    class Replace(
        val screen: Screen,
        val navigationType: NavigationType = DEFAULT,
        val sharedElements: Map<View, String>
    ) : Command()

    class Add(
        val screen: Screen,
        val navigationType: NavigationType = DEFAULT
    ) : Command()

    object Back : Command()
}

enum class NavigationType {
    DEFAULT,
    FULLSCREEN
}