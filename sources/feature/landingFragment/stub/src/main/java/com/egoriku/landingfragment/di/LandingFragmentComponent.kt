package com.egoriku.landingfragment.di

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.FragmentScope
import dagger.Component
import dagger.Module

@FragmentScope
@Component(dependencies = [ApplicationProvider::class], modules = [LandingFragmentModule::class])
internal interface LandingFragmentComponent

@Module
internal class LandingFragmentModule