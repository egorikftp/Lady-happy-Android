package com.egoriku.photoreportfragment.di.module

import com.egoriku.core.actions.IPhotoReportFragmentAction
import com.egoriku.photoreportfragment.action.PhotoReportFragmentAction
import dagger.Module
import dagger.Provides


@Module
class PhotoReportExportModule {

    @Provides
    fun providePhotoReportFragmentAction(): IPhotoReportFragmentAction = PhotoReportFragmentAction()
}
