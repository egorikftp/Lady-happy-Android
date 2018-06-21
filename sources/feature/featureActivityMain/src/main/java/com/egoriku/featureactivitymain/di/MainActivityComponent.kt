package com.egoriku.featureactivitymain.di

import com.egoriku.core.di.ActivityScope
import com.egoriku.core.di.ApplicationProvider
import com.egoriku.featureactivitymain.MainActivityTest
import com.egoriku.featureactivitymain.di.module.MainActivityModule
import dagger.Component

@ActivityScope
@Component(
        dependencies = [ApplicationProvider::class],
        modules = [MainActivityModule::class]
)
interface MainActivityComponent {

    fun inject(activity: MainActivityTest)

    class Initializer private constructor() {
        companion object {

            fun init(applicationProvider: ApplicationProvider): MainActivityComponent {
                return DaggerMainActivityComponent.builder()
                        .applicationProvider(applicationProvider)
                        .build()
            }
        }
    }
}