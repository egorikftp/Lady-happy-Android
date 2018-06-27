package com.egoriku.core.di.utils

interface IRouter {

    fun navigateTo(screen: String?)

    fun newRootScreen(screen: String?)

    fun exit()
}