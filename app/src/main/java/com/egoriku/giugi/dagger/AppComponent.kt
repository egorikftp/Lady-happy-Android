package com.egoriku.giugi.dagger

import com.egoriku.giugi.dagger.module.NavigationModule
import com.egoriku.giugi.ui.activity.MainActivity
import com.egoriku.giugi.ui.activity.fragment.OverviewFragment
import com.egoriku.giugi.ui.activity.start.StartActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(NavigationModule::class))
interface AppComponent {

    fun inject(activity: StartActivity)

    fun inject(activity: MainActivity)

    fun inject(fragment: OverviewFragment)
}
