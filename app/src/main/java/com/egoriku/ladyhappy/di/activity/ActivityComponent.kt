package com.egoriku.ladyhappy.di.activity

import com.egoriku.core.di.ActivityScope
import com.egoriku.featureactivitymain.di.module.MainActivityModule
import com.egoriku.ladyhappy.di.application.AppComponent
import com.egoriku.ladyhappy.presentation.activity.main.MainActivity
import com.egoriku.ladyhappy.presentation.activity.main.MainActivityContract
import dagger.Component


@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [MainActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(mainActivityPresenter: MainActivityContract.Presenter)
}