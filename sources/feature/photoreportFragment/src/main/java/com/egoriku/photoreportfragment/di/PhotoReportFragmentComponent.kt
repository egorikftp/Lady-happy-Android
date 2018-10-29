package com.egoriku.photoreportfragment.di

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.FragmentScope
import com.egoriku.photoreportfragment.di.module.PhotoReportModule
import com.egoriku.photoreportfragment.presentation.PhotoReportFragment
import dagger.Component

@FragmentScope
@Component(
        dependencies = [ApplicationProvider::class],
        modules = [PhotoReportModule::class]
)
internal interface PhotoReportFragmentComponent {

    fun inject(fragment: PhotoReportFragment)

    companion object {
        fun init(applicationProvider: ApplicationProvider): PhotoReportFragmentComponent {
            return DaggerPhotoReportFragmentComponent.builder()
                    .applicationProvider(applicationProvider)
                    .build()
        }
    }
}