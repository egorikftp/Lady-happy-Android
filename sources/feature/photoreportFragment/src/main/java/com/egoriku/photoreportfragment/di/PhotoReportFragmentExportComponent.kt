package com.egoriku.photoreportfragment.di

import com.egoriku.core.di.PhotoReportFragmentProvider
import com.egoriku.photoreportfragment.di.module.PhotoReportExportModule
import dagger.Component

@Component(modules = [PhotoReportExportModule::class])
interface PhotoReportFragmentExportComponent : PhotoReportFragmentProvider {

    class Initializer private constructor() {
        companion object {
            fun init(): PhotoReportFragmentProvider = DaggerPhotoReportFragmentExportComponent.create()
        }
    }
}