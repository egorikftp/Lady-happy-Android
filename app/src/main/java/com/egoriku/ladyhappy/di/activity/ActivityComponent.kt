package com.egoriku.ladyhappy.di.activity

import com.egoriku.core.di.ActivityScope
import com.egoriku.featureactivitymain.di.module.MainActivityModule
import com.egoriku.featureactivitymain.presentation.activity.MainActivity
import com.egoriku.featureactivitymain.presentation.activity.MainActivityContract
import com.egoriku.ladyhappy.di.application.AppComponent
import dagger.Component


@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [MainActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(mainActivityPresenter: MainActivityContract.Presenter)
}