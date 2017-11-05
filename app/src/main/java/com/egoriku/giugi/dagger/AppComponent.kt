package com.egoriku.giugi.dagger

import com.egoriku.giugi.dagger.module.LocalNavigationModule
import com.egoriku.giugi.dagger.module.NavigationModule
import com.egoriku.giugi.ui.activity.MainActivity
import com.egoriku.giugi.ui.activity.StartActivity
import com.egoriku.giugi.ui.fragment.allgoods.AllGoodsFragment
import com.egoriku.giugi.ui.fragment.order.OrderFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(NavigationModule::class, LocalNavigationModule::class))
interface AppComponent {

    fun inject(activity: StartActivity)

    fun inject(activity: MainActivity)

    fun inject(fragment: ContainerFragment)

    fun inject(fragment: AllGoodsFragment)

    fun inject(fragment: OrderFragment)

}
