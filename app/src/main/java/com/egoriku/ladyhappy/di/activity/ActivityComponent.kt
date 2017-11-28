package com.egoriku.ladyhappy.di.activity

import com.egoriku.ladyhappy.di.app.AppComponent
import com.egoriku.ladyhappy.di.scope.ActivityScope
import com.egoriku.ladyhappy.presentation.presenters.impl.LaunchPresenter
import com.egoriku.ladyhappy.presentation.presenters.impl.MainActivityPresenter
import com.egoriku.ladyhappy.presentation.ui.activity.LaunchActivity
import com.egoriku.ladyhappy.presentation.ui.activity.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(activity: LaunchActivity)
    fun inject(launchPresenter: LaunchPresenter)

    fun inject(activity: MainActivity)
    fun inject(mainActivityPresenter: MainActivityPresenter)
}