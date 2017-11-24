package com.egoriku.ladyhappy.module

import com.egoriku.ladyhappy.presentation.ui.activity.MainActivity
import com.egoriku.ladyhappy.presentation.ui.activity.StartActivity
import com.egoriku.ladyhappy.presentation.ui.fragments.AllGoodsFragment
import com.egoriku.ladyhappy.presentation.ui.fragments.order.OrderFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NavigationModule::class, LocalNavigationModule::class))
interface AppComponent2 {

    fun inject(activity: StartActivity)

    fun inject(activity: MainActivity)

    fun inject(fragment: AllGoodsFragment)

    fun inject(fragment: OrderFragment)

}
