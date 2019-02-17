package com.egoriku.mainscreen.di

import com.egoriku.core.di.ActivityScope
import com.egoriku.core.di.ApplicationProvider
import com.egoriku.mainscreen.di.module.MainActivityModule
import com.egoriku.mainscreen.presentation.activity.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationProvider::class], modules = [MainActivityModule::class])
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    companion object {
        fun init(applicationProvider: ApplicationProvider): MainActivityComponent {
            return DaggerMainActivityComponent.builder()
                    .applicationProvider(applicationProvider)
                    .build()
        }
    }
}