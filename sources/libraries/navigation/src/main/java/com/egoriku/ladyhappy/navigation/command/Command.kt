package com.egoriku.ladyhappy.navigation.command

import com.egoriku.ladyhappy.navigation.screen.Screen

sealed class Command

class Replace(val screen: Screen): Command()

class Add(val screen: Screen): Command()

class Back : Command()