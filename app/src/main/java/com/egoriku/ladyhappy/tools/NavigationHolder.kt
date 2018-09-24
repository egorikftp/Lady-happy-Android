package com.egoriku.ladyhappy.tools

import com.egoriku.core.di.utils.INavigationHolder
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class NavigationHolder
@Inject constructor(private val navigatorHolder: NavigatorHolder) : INavigationHolder {

    override fun setNavigator(navigator: Navigator) = navigatorHolder.setNavigator(navigator)

    override fun removeNavigator() = navigatorHolder.removeNavigator()
}