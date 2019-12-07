package com.egoriku.ladyhappy.landing.di

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationProvider::class])
internal interface LandingFragmentComponent {

    companion object {
        fun init(applicationProvider: ApplicationProvider): LandingFragmentComponent =
                DaggerLandingFragmentComponent.builder()
                        .applicationProvider(applicationProvider)
                        .build()
    }
}