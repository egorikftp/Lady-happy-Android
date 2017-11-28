package com.egoriku.ladyhappy.di.app

import com.egoriku.ladyhappy.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    private var cicerone: Cicerone<Router> = Cicerone.create()

    @ApplicationScope
    @Provides
    fun provideRouter(): Router = cicerone.router

    @ApplicationScope
    @Provides
    fun provideNavigationHolder(): NavigatorHolder = cicerone.navigatorHolder
}