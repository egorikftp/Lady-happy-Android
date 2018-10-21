package com.egoriku.photoreportfragment.di

import com.egoriku.core.actions.IPhotoReportFragmentAction
import com.egoriku.core.di.PhotoReportFragmentProvider
import com.egoriku.photoreportfragment.action.PhotoReportFragmentAction
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [PhotoReportExportModule::class])
interface PhotoReportFragmentExportComponent : PhotoReportFragmentProvider {

    class Initializer private constructor() {
        companion object {
            fun init(): PhotoReportFragmentProvider = DaggerPhotoReportFragmentExportComponent.create()
        }
    }
}

@Module
class PhotoReportExportModule {

    @Provides
    fun providePhotoReportFragmentAction(): IPhotoReportFragmentAction = PhotoReportFragmentAction()
}
