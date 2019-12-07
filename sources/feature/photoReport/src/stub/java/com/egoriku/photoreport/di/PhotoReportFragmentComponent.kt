package com.egoriku.photoreport.di

import com.egoriku.core.di.ApplicationProvider
import com.egoriku.core.di.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationProvider::class])
internal interface PhotoReportFragmentComponent {

    companion object {
        fun init(applicationProvider: ApplicationProvider): PhotoReportFragmentComponent {
            return DaggerPhotoReportFragmentComponent.builder()
                    .applicationProvider(applicationProvider)
                    .build()
        }
    }
}