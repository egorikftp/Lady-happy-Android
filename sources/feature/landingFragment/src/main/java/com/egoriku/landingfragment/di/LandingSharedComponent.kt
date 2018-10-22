package com.egoriku.landingfragment.di

import com.egoriku.core.actions.ILandingFragmentAction
import com.egoriku.core.di.LandingFeatureProvider
import com.egoriku.landingfragment.action.LandingFragmentAction
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [LandingSharedModule::class])
interface LandingSharedComponent : LandingFeatureProvider {

    companion object {
        fun init(): LandingFeatureProvider = DaggerLandingSharedComponent.create()
    }
}

@Module
class LandingSharedModule {
    @Provides
    fun provideAction(): ILandingFragmentAction = LandingFragmentAction()
}
