package com.egoriku.ladyhappy.tools

import com.egoriku.ladyhappy.core.INavigationHolder
import com.egoriku.ladyhappy.navigation.navigator.INavigator
import com.egoriku.ladyhappy.navigation.navigator.NavigatorHolder

internal class NavigationHolder(
    private val navigatorHolder: NavigatorHolder
) : INavigationHolder {

    override fun setNavigator(navigator: INavigator) = navigatorHolder.setNavigator(navigator)

    override fun removeNavigator() = navigatorHolder.removeNavigator()
}