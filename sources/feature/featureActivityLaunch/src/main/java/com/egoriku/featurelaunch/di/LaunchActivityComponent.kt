package com.egoriku.featurelaunch.di

import com.egoriku.core.di.ActivityScope
import com.egoriku.core.di.ApplicationProvider
import com.egoriku.featurelaunch.presentation.activity.LaunchActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationProvider::class])
internal interface LaunchActivityComponent {

    fun inject(activity: LaunchActivity)

    companion object {
        fun init(applicationProvider: ApplicationProvider): LaunchActivityComponent =
                DaggerLaunchActivityComponent.builder()
                        .applicationProvider(applicationProvider).build()
    }
}