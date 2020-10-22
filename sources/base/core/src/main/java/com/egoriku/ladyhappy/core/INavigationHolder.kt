package com.egoriku.ladyhappy.core

import com.egoriku.ladyhappy.navigation.navigator.INavigator

interface INavigationHolder {

    fun setNavigator(navigator: INavigator)

    fun removeNavigator()
}