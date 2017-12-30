package com.egoriku.ladyhappy.di.activity

import com.egoriku.ladyhappy.di.app.AppComponent
import com.egoriku.ladyhappy.di.scope.ActivityScope
import com.egoriku.ladyhappy.presentation.activity.launch.LaunchPresenter
import com.egoriku.ladyhappy.presentation.activity.main.MainActivityPresenter
import com.egoriku.ladyhappy.presentation.activity.launch.LaunchActivity
import com.egoriku.ladyhappy.presentation.activity.launch.LaunchContract
import com.egoriku.ladyhappy.presentation.activity.main.MainActivity
import com.egoriku.ladyhappy.presentation.activity.main.MainActivityContract
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: LaunchActivity)
    fun inject(activity: MainActivity)

    fun inject(launchPresenter: LaunchContract.Presenter)
    fun inject(mainActivityPresenter: MainActivityContract.Presenter)
}