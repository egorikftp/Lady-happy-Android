package com.egoriku.photoreportfragment.di

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.FragmentScope
import com.egoriku.photoreportfragment.di.module.PhotoReportModule
import dagger.Component

@FragmentScope
@Component(
        dependencies = [ApplicationProvider::class],
        modules = [PhotoReportModule::class]
)
internal interface PhotoReportFragmentComponent