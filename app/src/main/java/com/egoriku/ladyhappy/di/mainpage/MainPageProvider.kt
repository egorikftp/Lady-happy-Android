package com.egoriku.ladyhappy.di.mainpage

import com.egoriku.ladyhappy.presentation.fragment.main.MainPageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainPageProvider {
    @ContributesAndroidInjector(modules = [MainPageModule::class])
    abstract fun provideMainPageFragment(): MainPageFragment
}