package com.egoriku.ladyhappy.di.app

import com.egoriku.ladyhappy.di.activity.MainActivityModule
import com.egoriku.ladyhappy.di.allgoods.AllGoodsFragmentProvider
import com.egoriku.ladyhappy.di.mainpage.MainPageProvider
import com.egoriku.ladyhappy.di.order.OrderFragmentProvider
import com.egoriku.ladyhappy.presentation.activity.launch.LaunchActivity
import com.egoriku.ladyhappy.presentation.activity.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
        MainActivityModule::class,
        AllGoodsFragmentProvider::class,
        OrderFragmentProvider::class,
        MainPageProvider::class
    ])
    abstract fun bindMainActivity(): MainActivity
}