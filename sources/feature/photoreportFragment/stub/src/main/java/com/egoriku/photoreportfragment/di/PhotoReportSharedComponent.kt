package com.egoriku.photoreportfragment.di

import com.egoriku.core.actions.IPhotoReportFragmentAction
import com.egoriku.core.di.PhotoReportFeatureProvider
import com.egoriku.photoreportfragment.action.PhotoReportFragmentAction
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [PhotoReportExportModule::class])
interface PhotoReportSharedComponent : PhotoReportFeatureProvider {

    companion object {
        fun init(): PhotoReportFeatureProvider = DaggerPhotoReportSharedComponent.create()
    }
}

@Module
class PhotoReportExportModule {

    @Provides
    fun providePhotoReportFragmentAction(): IPhotoReportFragmentAction = PhotoReportFragmentAction()
}
