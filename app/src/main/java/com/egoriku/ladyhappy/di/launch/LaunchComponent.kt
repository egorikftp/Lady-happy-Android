package com.egoriku.ladyhappy.di.launch

import com.egoriku.ladyhappy.di.app.AppComponent
import com.egoriku.ladyhappy.di.scope.ActivityScope
import com.egoriku.ladyhappy.presentation.ui.activity.LaunchActivity
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class))
interface LaunchComponent {

    fun inject(activity: LaunchActivity)
}