package com.egoriku.featureactivitymain.di

import com.egoriku.core.actions.IMainActivityAction
import com.egoriku.core.di.MainActivityFeatureProvider
import com.egoriku.featureactivitymain.actions.MainActivityAction
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [MainActivitySharedModule::class])
interface MainActivityFeatureSharedComponent : MainActivityFeatureProvider {
    companion object {
        fun init(): MainActivityFeatureProvider = DaggerMainActivityFeatureSharedComponent.create()
    }
}

@Module
class MainActivitySharedModule {

    @Provides
    fun provideAction(): IMainActivityAction = MainActivityAction()
}