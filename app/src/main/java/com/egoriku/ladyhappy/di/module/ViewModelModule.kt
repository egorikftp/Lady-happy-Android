package com.egoriku.ladyhappy.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egoriku.core.di.ViewModelKey
import com.egoriku.ladyhappy.di.viewmodel.AppViewModelFactory
import com.egoriku.landing.presentation.LandingViewModel
import com.egoriku.mainscreen.presentation.activity.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun provideMaindActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LandingViewModel::class)
    abstract fun provideLandingViewModel(landingViewModel: LandingViewModel): ViewModel
}