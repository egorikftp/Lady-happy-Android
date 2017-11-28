package com.egoriku.ladyhappy.di.launch

import com.egoriku.ladyhappy.di.app.AppComponent
import com.egoriku.ladyhappy.di.scope.ActivityScope
import com.egoriku.ladyhappy.presentation.presenters.impl.LaunchPresenter
import com.egoriku.ladyhappy.presentation.ui.activity.LaunchActivity
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(LaunchModule::class))
interface LaunchComponent {

    fun inject(activity: LaunchActivity)
    fun inject(launchPresenter: LaunchPresenter)

 /*   fun inject(router: Router)
    fun inject(navigatorHolder: NavigatorHolder)*/
}