package com.egoriku.ladyhappy.di.module

import com.egoriku.core.di.ApplicationScope
import com.egoriku.core.di.utils.INavigationHolder
import com.egoriku.core.di.utils.IRouter
import com.egoriku.ladyhappy.navigation.NavigateMe
import com.egoriku.ladyhappy.tools.NavigationHolder
import com.egoriku.ladyhappy.tools.Router
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    private var heyNav = NavigateMe.create()

    @ApplicationScope
    @Provides
    fun provideRouter(): IRouter = Router(heyNav.router)

    @ApplicationScope
    @Provides
    fun provideNavigationHolder(): INavigationHolder = NavigationHolder(heyNav.navigatorHolder)
}