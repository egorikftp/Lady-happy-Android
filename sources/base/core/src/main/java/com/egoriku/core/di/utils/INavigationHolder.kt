package com.egoriku.core.di.utils

import com.egoriku.ladyhappy.navigation.navigator.INavigator

interface INavigationHolder {

    fun setNavigator(navigator: INavigator)

    fun removeNavigator()
}