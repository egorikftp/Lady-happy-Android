package com.egoriku.mainfragment.di

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.FragmentScope
import com.egoriku.mainfragment.di.module.MainFragmentModule
import com.egoriku.mainfragment.presentation.fragment.MainPageFragment
import dagger.Component

@FragmentScope
@Component(
        dependencies = [ApplicationProvider::class],
        modules = [MainFragmentModule::class]
)
interface MainFragmentComponent {

    fun inject(fragment: MainPageFragment)

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