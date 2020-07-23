package com.egoriku.ladyhappy.landing.di

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.FragmentScope
import com.egoriku.ladyhappy.landing.presentation.LandingPageFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationProvider::class])
internal interface LandingFragmentComponent {

    fun inject(fragment: LandingPageFragment)

    companion object {
        fun init(applicationProvider: ApplicationProvider): LandingFragmentComponent =
                DaggerLandingFragmentComponent.builder()
                        .applicationProvider(applicationProvider)
                        .build()
    }
}