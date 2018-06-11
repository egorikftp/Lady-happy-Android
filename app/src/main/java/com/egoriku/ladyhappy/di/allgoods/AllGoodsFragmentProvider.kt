package com.egoriku.ladyhappy.di.allgoods

import com.egoriku.ladyhappy.presentation.fragment.allgoods.AllGoodsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AllGoodsFragmentProvider {

    @ContributesAndroidInjector(modules = [AllGoodsModule::class])
    abstract fun provideAllGoodsFragment(): AllGoodsFragment

}