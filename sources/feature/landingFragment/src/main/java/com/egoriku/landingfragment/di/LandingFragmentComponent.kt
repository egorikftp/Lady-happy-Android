package com.egoriku.landingfragment.di

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.FragmentScope
import com.egoriku.landingfragment.di.module.LandingFragmentModule
import com.egoriku.landingfragment.presentation.LandingPageFragment
import dagger.Component

@FragmentScope
@Component(
        dependencies = [ApplicationProvider::class],
        modules = [LandingFragmentModule::class]
)
internal interface LandingFragmentComponent {

    fun inject(fragment: LandingPageFragment)

    companion object {
        fun init(applicationProvider: ApplicationProvider): LandingFragmentComponent =
                DaggerLandingFragmentComponent.builder()
                        .applicationProvider(applicationProvider)
                        .build()
    }
}