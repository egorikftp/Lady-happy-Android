package com.egoriku.ladyhappy.di.order

import com.egoriku.ladyhappy.presentation.fragment.order.OrderFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class OrderFragmentProvider {

    @ContributesAndroidInjector(modules = [OrderModule::class])
    abstract fun provideOrderFragment(): OrderFragment

}