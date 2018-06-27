package com.egoriku.core.di.utils

import ru.terrakok.cicerone.Navigator

interface INavigationHolder {

    fun setNavigator(navigator: Navigator)

    fun removeNavigator()
}