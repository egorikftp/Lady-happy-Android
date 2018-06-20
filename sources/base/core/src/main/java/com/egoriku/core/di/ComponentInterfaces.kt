package com.egoriku.core.di

import com.egoriku.core.IApp
import com.egoriku.core.actions.ShowMainScreenAction

interface ApplicationProvider :
        MainToolsProvider,
        ActionsProvider


interface MainToolsProvider {
    fun provideContext(): IApp
}

interface ActionsProvider {
    fun provideShowMainScreenAction(): ShowMainScreenAction
}