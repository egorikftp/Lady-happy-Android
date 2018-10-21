package com.egoriku.photoreportfragment.di

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.FragmentScope
import dagger.Component
import dagger.Module

@FragmentScope
@Component(
        dependencies = [ApplicationProvider::class],
        modules = [PhotoReportModule::class]
)
internal interface PhotoReportFragmentComponent

@Module
class PhotoReportModule