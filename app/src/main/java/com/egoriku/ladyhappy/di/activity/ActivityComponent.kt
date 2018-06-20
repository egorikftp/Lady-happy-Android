package com.egoriku.ladyhappy.di.activity

import com.egoriku.core.di.ActivityScope
import com.egoriku.ladyhappy.di.app.AppComponent
import com.egoriku.ladyhappy.presentation.activity.main.MainActivity
import com.egoriku.ladyhappy.presentation.activity.main.MainActivityContract
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(mainActivityPresenter: MainActivityContract.Presenter)
}