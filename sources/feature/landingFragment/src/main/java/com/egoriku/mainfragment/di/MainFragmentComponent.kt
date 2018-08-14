package com.egoriku.mainfragment.di

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.FragmentScope
import com.egoriku.mainfragment.di.module.LandingFragmentModule
import com.egoriku.mainfragment.presentation.LandingPageFragment
import dagger.Component

@FragmentScope
@Component(
        dependencies = [ApplicationProvider::class],
        modules = [LandingFragmentModule::class]
)
internal interface MainFragmentComponent {

    fun inject(fragment: LandingPageFragment)

    class Initializer private constructor() {
        companion object {

            fun init(applicationProvider: ApplicationProvider): MainFragmentComponent {
                return DaggerMainFragmentComponent.builder()
                        .applicationProvider(applicationProvider)
                        .build()
            }
        }
    }
}